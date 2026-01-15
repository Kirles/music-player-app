package com.example.backend.service;

import com.example.backend.dao.ReleaseDAO;
import com.example.backend.dto.*;
import com.example.backend.entity.Release;
import com.example.backend.entity.Track;
import com.example.backend.mapper.ReleaseMapper;
import com.example.backend.mapper.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service
@Transactional
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseDAO releaseDao;

    private final ReleaseMapper releaseMapper;

    private final TrackMapper trackMapper;

    private final ArtistService artistService;

    @Autowired
    public ReleaseServiceImpl(ReleaseDAO releaseDao,
                              ReleaseMapper releaseMapper,
                              TrackMapper trackMapper,
                              ArtistService artistService) {

        this.releaseDao = releaseDao;
        this.releaseMapper = releaseMapper;
        this.trackMapper = trackMapper;
        this.artistService = artistService;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "allReleases", allEntries = true)
    })
    public ReleaseDTO createRelease(CreateReleaseRequest request) {
        Release release = releaseMapper.toEntity(request);
        release.setArtist(artistService.getArtistEntityById(request.getArtistId()));

        for (TrackDTO trackDTO : request.getTracks()){
            Track track = trackMapper.toEntity(trackDTO);
            track.setRelease(release);
            release.addTrack(track);
        }

        return releaseMapper.toDto(releaseDao.create(release));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "releaseById", key = "#dto.id"),
            @CacheEvict(cacheNames = "allReleases", allEntries = true),
            @CacheEvict(cacheNames = "releaseWithTracks", key = "#dto.id")
    })
    public ReleaseDTO updateRelease(ReleaseDTO dto) {
        return releaseMapper.toDto(releaseDao.update(releaseMapper.toEntity(dto)));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "releaseById", key = "#id"),
            @CacheEvict(cacheNames = "allReleases", allEntries = true),
            @CacheEvict(cacheNames = "releaseWithTracks", key = "#id")
    })
    public void deleteRelease(Long id) {
        releaseDao.delete(id);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "releaseById", key = "#id")
    public ReleaseDTO getReleaseById(Long id) {
        return releaseMapper.toDto(releaseDao.findById(id).orElseThrow(() -> new RuntimeException("Release not found!")));
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "allReleases")
    public List<ReleaseDTO> getAllReleases() {
        return releaseMapper.toDtoList(releaseDao.findAll());
    }

    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "releaseWithTracks", key = "#id")
    public ReleaseWithTracksDTO getReleaseByIdWithTracks(Long id) {
        return releaseMapper.toDtoWithTracks(releaseDao.findByIdWithTracks(id)
                        .orElseThrow(() -> new RuntimeException("Release not found!")));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "releaseById", key = "#id"),
            @CacheEvict(cacheNames = "allReleases", allEntries = true),
            @CacheEvict(cacheNames = "releaseWithTracks", key = "#id")
    })
    public ReleaseWithTracksDTO addTrackToRelease(Long id, TrackDTO trackDTO) {
        Release release = releaseDao.findByIdWithTracks(id).orElseThrow(() -> new RuntimeException("Release not found!"));
        Track track = trackMapper.toEntity(trackDTO);
        track.setRelease(release);
        release.addTrack(track);
        Release updated = releaseDao.update(release);
        return releaseMapper.toDtoWithTracks(updated);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "releaseById", key = "#releaseId"),
            @CacheEvict(cacheNames = "allReleases", allEntries = true),
            @CacheEvict(cacheNames = "releaseWithTracks", key = "#releaseId")
    })
    public ReleaseWithTracksDTO deleteTrackFromRelease(Long releaseId, Long trackId) {
        Release release = releaseDao.findByIdWithTracks(releaseId).orElseThrow(() -> new RuntimeException("Release not found!"));
        Iterator<Track> iterator = release.getTracks().iterator();
        while(iterator.hasNext()) {
            Track track = iterator.next();
            if(track.getId().equals(trackId)){
                release.deleteTrack(track);
                return releaseMapper.toDtoWithTracks(release);
            }
        }
        throw new RuntimeException("Release not found!");
    }
}

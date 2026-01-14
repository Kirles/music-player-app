package com.example.backend.service;

import com.example.backend.dao.ReleaseDAO;
import com.example.backend.dto.ReleaseDTO;
import com.example.backend.dto.ReleaseWithTracksDTO;
import com.example.backend.dto.TrackDTO;
import com.example.backend.entity.Release;
import com.example.backend.entity.Track;
import com.example.backend.mapper.ReleaseMapper;
import com.example.backend.mapper.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseDAO releaseDao;

    private final ReleaseMapper releaseMapper;

    private final TrackMapper trackMapper;

    @Autowired
    public ReleaseServiceImpl(ReleaseDAO releaseDao, ReleaseMapper releaseMapper, TrackMapper trackMapper) {
        this.releaseDao = releaseDao;
        this.releaseMapper = releaseMapper;
        this.trackMapper = trackMapper;
    }

    public ReleaseDTO createRelease(ReleaseDTO dto) {
        return releaseMapper.toDto(releaseDao.create(releaseMapper.toEntity(dto)));
    }

    public ReleaseDTO updateRelease(ReleaseDTO dto) {
        return releaseMapper.toDto(releaseDao.update(releaseMapper.toEntity(dto)));
    }

    public void deleteRelease(ReleaseDTO dto) {
        releaseDao.delete(releaseMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public ReleaseDTO getReleaseById(Long id) {
        return releaseMapper.toDto(releaseDao.findById(id).orElseThrow(() -> new RuntimeException("Release not found!")));
    }

    @Transactional(readOnly = true)
    public List<ReleaseDTO> getAllReleases() {
        return releaseMapper.toDtoList(releaseDao.findAll());
    }

    @Transactional(readOnly = true)
    public ReleaseWithTracksDTO getReleaseByIdWithTracks(Long id) {
        return releaseMapper.toDtoWithTracks(releaseDao.findByIdWithTracks(id)
                        .orElseThrow(() -> new RuntimeException("Release not found!")));
    }

    public ReleaseWithTracksDTO addTrackToRelease(Long id, TrackDTO trackDTO) {
        Release release = releaseDao.findByIdWithTracks(id).orElseThrow(() -> new RuntimeException("Release not found!"));
        Track track = trackMapper.toEntity(trackDTO);
        track.setRelease(release);
        release.addTrack(track);
        Release updated = releaseDao.update(release);
        return releaseMapper.toDtoWithTracks(updated);
    }

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

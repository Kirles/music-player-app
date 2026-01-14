package com.example.backend.service;

import com.example.backend.dao.ArtistDAO;
import com.example.backend.dto.ArtistDTO;
import com.example.backend.mapper.ArtistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDAO artistDao;

    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistServiceImpl(ArtistDAO artistDao, ArtistMapper artistMapper){
        this.artistDao = artistDao;
        this.artistMapper = artistMapper;
    }

    public ArtistDTO createArtist(ArtistDTO dto) {
        return artistMapper.toDto(artistDao.create(artistMapper.toEntity(dto)));
    }

    public ArtistDTO updateArtist(ArtistDTO dto) {
        return artistMapper.toDto(artistDao.update(artistMapper.toEntity(dto)));
    }

    public void deleteArtist(ArtistDTO dto) {
        artistDao.delete(artistMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public ArtistDTO getArtistById(Long id) {
        return artistMapper.toDto(artistDao.findById(id).orElseThrow(() -> new RuntimeException("Artist not found!")));
    }

    @Transactional(readOnly = true)
    public List<ArtistDTO> getAllArtists() {
        return artistMapper.toDtoList(artistDao.findAll());
    }
}

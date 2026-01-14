package com.example.backend.service;

import com.example.backend.dao.TrackDAO;
import com.example.backend.dto.TrackDTO;
import com.example.backend.entity.Track;
import com.example.backend.mapper.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrackServiceImpl implements TrackService {

    private final TrackDAO trackDAO;

    private final TrackMapper trackMapper;

    @Autowired
    public TrackServiceImpl(TrackDAO trackDAO, TrackMapper trackMapper) {
        this.trackDAO = trackDAO;
        this.trackMapper = trackMapper;
    }

    public TrackDTO createTrack(TrackDTO dto) {
        return trackMapper.toDto(trackDAO.create(trackMapper.toEntity(dto)));
    }

    public TrackDTO updateTrack(TrackDTO dto) {
        return trackMapper.toDto(trackDAO.update(trackMapper.toEntity(dto)));
    }

    public void deleteTrack(TrackDTO dto) {
        trackDAO.delete(trackMapper.toEntity(dto));
    }

    @Transactional(readOnly = true)
    public TrackDTO getTrackById(Long id) {
        return trackMapper.toDto(trackDAO.findById(id).orElseThrow(() -> new RuntimeException("Track not found!")));
    }

    @Transactional(readOnly = true)
    public List<TrackDTO> getAllTracks() {
        return trackMapper.toDtoList(trackDAO.findAll());
    }

}

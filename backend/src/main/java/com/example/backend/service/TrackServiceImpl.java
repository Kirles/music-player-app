package com.example.backend.service;

import com.example.backend.dao.TrackDAO;
import com.example.backend.dto.TrackDTO;
import com.example.backend.mapper.TrackMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public TrackDTO getTrackById(Long id) {
        return trackMapper.toDto(trackDAO.findById(id).orElseThrow(() -> new RuntimeException("Track not found!")));
    }

}

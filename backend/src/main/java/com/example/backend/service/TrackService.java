package com.example.backend.service;

import com.example.backend.dto.TrackDTO;
import com.example.backend.entity.Track;

import java.util.List;
import java.util.Optional;

public interface TrackService {

    TrackDTO createTrack(TrackDTO track);

    TrackDTO updateTrack(TrackDTO track);

    void deleteTrack(TrackDTO track);

    TrackDTO getTrackById(Long id);

    List<TrackDTO> getAllTracks();
}

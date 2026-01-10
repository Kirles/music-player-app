package com.example.backend.service;

import com.example.backend.entity.Track;

import java.util.List;

public interface TrackService {

    void createTrack(Track track);

    void updateTrack(Track track);

    void deleteTrack(Track track);

    Track getTrackById(Long id);

    List<Track> getAllTracks();
}

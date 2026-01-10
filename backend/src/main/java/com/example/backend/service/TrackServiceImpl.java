package com.example.backend.service;

import com.example.backend.dao.TrackDAO;
import com.example.backend.entity.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrackServiceImpl {

    private final TrackDAO trackDAO;

    @Autowired
    public TrackServiceImpl(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public void createTrack(Track track) {
        trackDAO.create(track);
    }

    public void updateTrack(Track track) {
        trackDAO.update(track);
    }

    public void deleteTrack(Track track) {
        trackDAO.delete(track);
    }

    @Transactional(readOnly = true)
    public Track getTrackById(Long id) {
        return trackDAO.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Track> getAllTracks() {
        return trackDAO.findAll();
    }

}

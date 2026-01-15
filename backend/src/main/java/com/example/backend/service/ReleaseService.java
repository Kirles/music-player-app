package com.example.backend.service;

import com.example.backend.dto.CreateReleaseRequest;
import com.example.backend.dto.ReleaseDTO;
import com.example.backend.dto.ReleaseWithTracksDTO;
import com.example.backend.dto.TrackDTO;

import java.util.List;
import java.util.Optional;

public interface ReleaseService {

    ReleaseDTO createRelease(CreateReleaseRequest request);

    ReleaseDTO updateRelease(ReleaseDTO release);

    void deleteRelease(Long id);

    ReleaseDTO getReleaseById(Long id);

    List<ReleaseDTO> getAllReleases();

    ReleaseWithTracksDTO getReleaseByIdWithTracks(Long id);

    ReleaseWithTracksDTO addTrackToRelease(Long id, TrackDTO trackDTO);

    ReleaseWithTracksDTO deleteTrackFromRelease(Long releaseId, Long trackId);

}

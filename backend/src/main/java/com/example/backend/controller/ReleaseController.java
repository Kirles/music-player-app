package com.example.backend.controller;

import com.example.backend.dto.ReleaseDTO;
import com.example.backend.dto.ReleaseWithTracksDTO;
import com.example.backend.dto.TrackDTO;
import com.example.backend.service.ReleaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist/releases")
public class ReleaseController {

    private final ReleaseService releaseService;

    public ReleaseController(ReleaseService releaseService) {
        this.releaseService = releaseService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseDTO> getReleaseById(@PathVariable Long id) {
        return ResponseEntity.ok(releaseService.getReleaseById(id));
    }

    @PostMapping
    public ResponseEntity<ReleaseDTO> createRelease(@RequestBody ReleaseDTO releaseDTO) {
        return ResponseEntity.ok(releaseService.createRelease(releaseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelease(@RequestBody ReleaseDTO releaseDTO) {
        releaseService.deleteRelease(releaseDTO);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<ReleaseDTO>> getAllReleases(){
        return ResponseEntity.ok(releaseService.getAllReleases());
    }

    @GetMapping("/with-tracks/{id}")
    public ResponseEntity<ReleaseWithTracksDTO> getReleaseWithTracks(@PathVariable Long id) {
        return ResponseEntity.ok(releaseService.getReleaseByIdWithTracks(id));
    }

    @PostMapping("/{id}/tracks")
    public ResponseEntity<ReleaseWithTracksDTO> addTrackToRelease(@PathVariable Long id, @RequestBody TrackDTO trackDTO) {
        return ResponseEntity.ok(releaseService.addTrackToRelease(id, trackDTO));
    }

    @DeleteMapping("/{releaseId}/tracks/{trackId}")
    public ResponseEntity<ReleaseWithTracksDTO> deleteTrackFromRelease(@PathVariable Long releaseId, @PathVariable Long trackId) {
        return ResponseEntity.ok(releaseService.deleteTrackFromRelease(releaseId, trackId));
    }
}

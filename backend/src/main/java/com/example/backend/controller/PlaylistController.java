package com.example.backend.controller;

import com.example.backend.dto.AddTrackToPlaylistRequest;
import com.example.backend.dto.CreatePlaylistRequest;
import com.example.backend.dto.PlaylistDTO;
import com.example.backend.dto.PlaylistWithTracksDTO;
import com.example.backend.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getPlaylistById(@PathVariable Long id) {
        PlaylistDTO dto = playlistService.getPlaylistById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody CreatePlaylistRequest request) {
        return ResponseEntity.ok(playlistService.createPlaylist(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/with-tracks/{id}")
    public ResponseEntity<PlaylistWithTracksDTO> getPlaylistWithTracksById(@PathVariable Long id) {
        return ResponseEntity.ok(playlistService.getPlaylistWithTracksById(id));
    }

    @PostMapping("/{id}/tracks")
    public ResponseEntity<PlaylistWithTracksDTO> addTrackToPlaylist(@PathVariable Long id, @RequestBody AddTrackToPlaylistRequest request) {
        return ResponseEntity.ok(playlistService.addTrackToPlaylist(id, request.getTrackId()));
    }

    @DeleteMapping("/{playlistId}/tracks/{trackId}")
    public ResponseEntity<PlaylistWithTracksDTO> deleteTrackFromPlaylist(@PathVariable Long playlistId, @PathVariable Long trackId) {
        return ResponseEntity.ok(playlistService.deleteTrackFromPlaylist(playlistId, trackId));
    }

}

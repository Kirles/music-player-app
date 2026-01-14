package com.example.backend.controller;

import com.example.backend.dto.PlaylistDTO;
import com.example.backend.service.PlaylistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<PlaylistDTO> createPlaylist(@RequestBody PlaylistDTO playlistDTO) {
        return ResponseEntity.ok(playlistService.createPlaylist(playlistDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@RequestBody PlaylistDTO playlistDTO) {
        playlistService.deletePlaylist(playlistDTO);
        return ResponseEntity.noContent().build();
    }

}

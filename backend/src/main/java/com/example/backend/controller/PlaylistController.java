package com.example.backend.controller;

import com.example.backend.dto.PlaylistDTO;
import com.example.backend.service.PlaylistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlaylistDTO> getUserById(@PathVariable Long id) {
        PlaylistDTO dto = playlistService.getPlaylistById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<PlaylistDTO> createUser(@RequestBody PlaylistDTO userDTO) {
        return ResponseEntity.ok(playlistService.createPlaylist(userDTO));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteUser(@RequestBody PlaylistDTO userDTO) {
        playlistService.deletePlaylist(userDTO);
        return HttpStatus.OK;
    }

}

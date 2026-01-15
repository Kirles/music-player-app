package com.example.backend.service;

import com.example.backend.dto.CreatePlaylistRequest;
import com.example.backend.dto.CreateReleaseRequest;
import com.example.backend.dto.PlaylistDTO;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {

    PlaylistDTO createPlaylist(CreatePlaylistRequest request);

    PlaylistDTO updatePlaylist(PlaylistDTO playlist);

    void deletePlaylist(Long id);

    PlaylistDTO getPlaylistById(Long id);

    List<PlaylistDTO> getAllPlaylists();

}

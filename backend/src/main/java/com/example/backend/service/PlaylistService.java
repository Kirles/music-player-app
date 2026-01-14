package com.example.backend.service;

import com.example.backend.dto.PlaylistDTO;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {

    PlaylistDTO createPlaylist(PlaylistDTO playlist);

    PlaylistDTO updatePlaylist(PlaylistDTO playlist);

    void deletePlaylist(PlaylistDTO playlist);

    PlaylistDTO getPlaylistById(Long id);

    List<PlaylistDTO> getAllPlaylists();

}

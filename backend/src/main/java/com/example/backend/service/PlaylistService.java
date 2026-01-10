package com.example.backend.service;

import com.example.backend.entity.Playlist;

import java.util.List;

public interface PlaylistService {

    void createPlaylist(Playlist playlist);

    void updatePlaylist(Playlist playlist);

    void deletePlaylist(Playlist playlist);

    Playlist getPlaylistById(Long id);

    List<Playlist> getAllPlaylists();

}

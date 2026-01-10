package com.example.backend.service;

import com.example.backend.dao.PlaylistDAO;
import com.example.backend.entity.Playlist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaylistServiceImpl {

    private final PlaylistDAO playlistDAO;

    @Autowired
    public PlaylistServiceImpl(PlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    public void createPlaylist(Playlist playlist) {
        playlistDAO.create(playlist);
    }

    public void updatePlaylist(Playlist playlist) {
        playlistDAO.update(playlist);
    }

    public void deletePlaylist(Playlist playlist) {
        playlistDAO.delete(playlist);
    }

    public Playlist getPlaylistByID(Long id) {
        return playlistDAO.findById(id);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistDAO.findAll();
    }

}

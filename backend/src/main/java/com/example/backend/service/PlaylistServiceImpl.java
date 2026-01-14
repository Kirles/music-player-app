package com.example.backend.service;

import com.example.backend.dao.PlaylistDAO;
import com.example.backend.dto.PlaylistDTO;
import com.example.backend.mapper.PlaylistMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistDAO playlistDAO;

    private final PlaylistMapper playlistMapper;

    @Autowired
    public PlaylistServiceImpl(PlaylistDAO playlistDAO, PlaylistMapper playlistMapper) {
        this.playlistDAO = playlistDAO;
        this.playlistMapper = playlistMapper;
    }

    public PlaylistDTO createPlaylist(PlaylistDTO dto) {
        return playlistMapper.toDto(playlistDAO.create(playlistMapper.toEntity(dto)));
    }

    public PlaylistDTO updatePlaylist(PlaylistDTO dto) {
        return playlistMapper.toDto(playlistDAO.update(playlistMapper.toEntity(dto)));
    }

    public void deletePlaylist(PlaylistDTO dto) {
        playlistDAO.delete(playlistMapper.toEntity(dto));
    }

    public PlaylistDTO getPlaylistById(Long id) {
        return playlistMapper.toDto(playlistDAO.findById(id).orElseThrow(() -> new RuntimeException("Playlist not found!")));
    }

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistMapper.toDtoList(playlistDAO.findAll());
    }

}

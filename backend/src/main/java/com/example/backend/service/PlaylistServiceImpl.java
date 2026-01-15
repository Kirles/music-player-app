package com.example.backend.service;

import com.example.backend.dao.PlaylistDAO;
import com.example.backend.dto.CreatePlaylistRequest;
import com.example.backend.dto.PlaylistDTO;
import com.example.backend.dto.TrackDTO;
import com.example.backend.entity.Playlist;
import com.example.backend.entity.Release;
import com.example.backend.entity.Track;
import com.example.backend.mapper.PlaylistMapper;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlaylistServiceImpl implements PlaylistService {

    private final PlaylistDAO playlistDAO;

    private final PlaylistMapper playlistMapper;

    private final UserService userService;

    @Autowired
    public PlaylistServiceImpl(PlaylistDAO playlistDAO, PlaylistMapper playlistMapper, UserService userService) {
        this.playlistDAO = playlistDAO;
        this.playlistMapper = playlistMapper;
        this.userService = userService;
    }

    public PlaylistDTO createPlaylist(CreatePlaylistRequest request) {

        Playlist playlist = playlistMapper.toEntity(request);
        playlist.setUser(userService.getUserEntityById(request.getUserId()));

        return playlistMapper.toDto(playlistDAO.create(playlist));
    }

    public PlaylistDTO updatePlaylist(PlaylistDTO dto) {
        return playlistMapper.toDto(playlistDAO.update(playlistMapper.toEntity(dto)));
    }

    public void deletePlaylist(Long id) {
        playlistDAO.delete(id);
    }

    public PlaylistDTO getPlaylistById(Long id) {
        return playlistMapper.toDto(playlistDAO.findById(id).orElseThrow(() -> new RuntimeException("Playlist not found!")));
    }

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistMapper.toDtoList(playlistDAO.findAll());
    }

}

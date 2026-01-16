package com.example.backend.service;

import com.example.backend.dao.PlaylistDAO;
import com.example.backend.dto.CreatePlaylistRequest;
import com.example.backend.dto.PlaylistDTO;
import com.example.backend.dto.PlaylistWithTracksDTO;
import com.example.backend.dto.TrackDTO;
import com.example.backend.entity.Playlist;
import com.example.backend.entity.Release;
import com.example.backend.entity.Track;
import com.example.backend.mapper.PlaylistMapper;
import com.example.backend.mapper.TrackMapper;
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

    private final TrackService trackService;

    @Autowired
    public PlaylistServiceImpl(PlaylistDAO playlistDAO,
                               PlaylistMapper playlistMapper,
                               UserService userService,
                               TrackService trackService) {
        this.playlistDAO = playlistDAO;
        this.playlistMapper = playlistMapper;
        this.userService = userService;
        this.trackService = trackService;
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

    public PlaylistWithTracksDTO getPlaylistWithTracksById(Long id) {
        return playlistMapper.toDtoWithTracks(playlistDAO.findByIdWithTracks(id).orElseThrow(() -> new RuntimeException("Playlist not found!")));
    }

    public List<PlaylistDTO> getAllPlaylists() {
        return playlistMapper.toDtoList(playlistDAO.findAll());
    }

    public PlaylistWithTracksDTO addTrackToPlaylist(Long playlistId, Long trackId) {
        Playlist playlist = playlistDAO.findByIdWithTracks(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found!"));
        Track track = trackService.getTrackEntityById(trackId);

        playlist.addTrack(track);

        return playlistMapper.toDtoWithTracks(playlistDAO.update(playlist));
    }

    public PlaylistWithTracksDTO deleteTrackFromPlaylist(Long playlistId, Long trackId) {
        Playlist playlist = playlistDAO.findByIdWithTracks(playlistId).orElseThrow(() -> new RuntimeException("Playlist not found!"));
        Track track = trackService.getTrackEntityById(trackId);

        playlist.deleteTrack(track);

        return playlistMapper.toDtoWithTracks(playlistDAO.update(playlist));
    }
}

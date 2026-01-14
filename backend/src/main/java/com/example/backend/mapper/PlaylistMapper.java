package com.example.backend.mapper;

import com.example.backend.dto.PlaylistDTO;
import com.example.backend.dto.PlaylistWithTracksDTO;
import com.example.backend.entity.Playlist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaylistMapper {

    PlaylistDTO toDto(Playlist playlist);

    Playlist toEntity(PlaylistDTO dto);

    List<PlaylistDTO> toDtoList(List<Playlist> playlists);

    List<Playlist> toEntityList(List<PlaylistDTO> dto);

    PlaylistWithTracksDTO toDtoWithTracks(Playlist playlist);

}

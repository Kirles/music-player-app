package com.example.backend.mapper;

import com.example.backend.dto.TrackDTO;
import com.example.backend.entity.Track;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TrackMapper {

    TrackDTO toDto(Track track);

    Track toEntity(TrackDTO dto);

    List<TrackDTO> toDtoList(List<Track> tracks);

    List<Track> toEntityList(List<TrackDTO> dto);

}

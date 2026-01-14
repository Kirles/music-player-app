package com.example.backend.mapper;

import com.example.backend.dto.ArtistDTO;
import com.example.backend.entity.Artist;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ArtistMapper {

    ArtistDTO toDto(Artist artist);

    Artist toEntity(ArtistDTO dto);

    List<ArtistDTO> toDtoList(List<Artist> artists);

    List<Artist> toEntityList(List<ArtistDTO> dto);
}

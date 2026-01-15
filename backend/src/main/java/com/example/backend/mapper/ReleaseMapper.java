package com.example.backend.mapper;

import com.example.backend.dto.CreateReleaseRequest;
import com.example.backend.dto.ReleaseDTO;
import com.example.backend.dto.ReleaseWithTracksDTO;
import com.example.backend.entity.Release;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReleaseMapper {

    ReleaseDTO toDto(Release release);

    Release toEntity(ReleaseDTO dto);

    @Mapping(target = "tracks", ignore = true)
    Release toEntity(CreateReleaseRequest dto);

    List<ReleaseDTO> toDtoList(List<Release> releases);

    List<Release> toEntityList(List<ReleaseDTO> dto);

    ReleaseWithTracksDTO toDtoWithTracks(Release release);

}

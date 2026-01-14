package com.example.backend.service;

import com.example.backend.dto.ArtistDTO;
import com.example.backend.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    ArtistDTO createArtist(ArtistDTO artist);

    ArtistDTO updateArtist(ArtistDTO artist);

    void deleteArtist(ArtistDTO artist);

    ArtistDTO getArtistById(Long id);

    List<ArtistDTO> getAllArtists();
}

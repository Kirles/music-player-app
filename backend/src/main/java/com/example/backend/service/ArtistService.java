package com.example.backend.service;

import com.example.backend.entity.Artist;

import java.util.List;

public interface ArtistService {

    void createArtist(Artist artist);

    void updateArtist(Artist artist);

    void deleteArtist(Artist artist);

    Artist getArtistById(Long id);

    List<Artist> getAllArtists();
}

package com.example.backend.service;

import com.example.backend.dao.ArtistDAO;
import com.example.backend.entity.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ArtistServiceImpl implements ArtistService {

    private final ArtistDAO artistDao;

    @Autowired
    public ArtistServiceImpl(ArtistDAO artistDao){
        this.artistDao = artistDao;
    }

    public void createArtist(Artist artist) {
        artistDao.create(artist);
    }

    public void updateArtist(Artist artist) {
        artistDao.update(artist);
    }

    public void deleteArtist(Artist artist) {
        artistDao.delete(artist);
    }

    @Transactional(readOnly = true)
    public Artist getArtistById(Long id) {
        return artistDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Artist> getAllArtists() {
        return artistDao.findAll();
    }
}

package com.example.backend.dao;

import com.example.backend.entity.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ArtistDAO {

    @PersistenceContext
    private final EntityManager em;

    public ArtistDAO(EntityManager em) {
        this.em = em;
    }

    public Artist create(Artist artist) {
        em.persist(artist);
        return artist;
    }

    public Artist update(Artist artist) {
        em.merge(artist);
        return artist;
    }

    public void delete(Artist artist) {
        em.remove(em.contains(artist)? artist : em.merge(artist));
    }

    public Optional<Artist> findById(Long id) {
        return Optional.ofNullable(em.find(Artist.class, id));
    }

    public List<Artist> findAll() {
        return em.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
    }

}

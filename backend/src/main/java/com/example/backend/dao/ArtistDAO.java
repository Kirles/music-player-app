package com.example.backend.dao;

import com.example.backend.entity.Artist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArtistDAO {

    @PersistenceContext
    private final EntityManager em;

    public ArtistDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Artist artist) {
        em.persist(artist);
    }

    public void update(Artist artist) {
        em.merge(artist);
    }

    public void delete(Artist artist) {
        em.remove(em.contains(artist)? artist : em.merge(artist));
    }

    public Artist findById(Long id) {
        return em.find(Artist.class, id);
    }

    public List<Artist> findAll() {
        return em.createQuery("SELECT a FROM Artist a", Artist.class).getResultList();
    }

}

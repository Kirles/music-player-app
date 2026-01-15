package com.example.backend.dao;

import com.example.backend.entity.Track;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TrackDAO {

    @PersistenceContext
    private EntityManager em;

    public TrackDAO(EntityManager em) {
        this.em = em;
    }

    public Track create(Track track) {
        em.persist(track);
        return track;
    }

    public Track update(Track track) {
        em.merge(track);
        return track;
    }

    public void delete(Long id) {
        em.remove(em.find(Track.class, id));
    }

    public Optional<Track> findById(Long id) {
        return Optional.ofNullable(em.find(Track.class, id));
    }

    public List<Track> findAll() {
        return em.createQuery("SELECT t FROM Track t", Track.class).getResultList();
    }

}

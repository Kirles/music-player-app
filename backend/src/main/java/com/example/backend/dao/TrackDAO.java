package com.example.backend.dao;

import com.example.backend.entity.Track;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrackDAO {

    @PersistenceContext
    private EntityManager em;

    public TrackDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Track track) {
        em.persist(track);
    }

    public void update(Track track) {
        em.merge(track);
    }

    public void delete(Track track) {
        em.remove(em.contains(track)? track : em.merge(track));
    }

    public Track findById(Long id) {
        return em.find(Track.class, id);
    }

    public List<Track> findAll() {
        return em.createQuery("SELECT t FROM Track t", Track.class).getResultList();
    }

}

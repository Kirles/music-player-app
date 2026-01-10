package com.example.backend.dao;

import com.example.backend.entity.Playlist;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaylistDAO {

    @PersistenceContext
    private EntityManager em;

    public PlaylistDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Playlist playlist) {
        em.persist(playlist);
    }

    public void update(Playlist playlist) {
        em.merge(playlist);
    }

    public void delete(Playlist playlist) {
        em.remove(em.contains(playlist)? playlist : em.merge(playlist));
    }

    public Playlist findById(Long id) {
        return em.find(Playlist.class, id);
    }

    public List<Playlist> findAll() {
        return em.createQuery("SELECT r FROM Release r", Playlist.class).getResultList();
    }

}

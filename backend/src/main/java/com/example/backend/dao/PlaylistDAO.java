package com.example.backend.dao;

import com.example.backend.entity.Playlist;
import com.example.backend.entity.Release;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PlaylistDAO {

    @PersistenceContext
    private EntityManager em;

    public PlaylistDAO(EntityManager em) {
        this.em = em;
    }

    public Playlist create(Playlist playlist) {
        em.persist(playlist);
        return playlist;
    }

    public Playlist update(Playlist playlist) {
        em.merge(playlist);
        return playlist;
    }

    public void delete(Long id) {
        em.remove(em.find(Playlist.class, id));
    }

    public Optional<Playlist> findById(Long id) {
        return Optional.ofNullable(em.find(Playlist.class, id));
    }

    public List<Playlist> findAll() {
        return em.createQuery("SELECT r FROM Release r", Playlist.class).getResultList();
    }

    public Optional<Playlist> findByIdWithTracks(Long id) {
        return em.createQuery(
                "SELECT p FROM Playlist p LEFT JOIN FETCH p.tracks pt WHERE p.id = :id ", Playlist.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }

}

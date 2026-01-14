package com.example.backend.dao;

import com.example.backend.entity.Release;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReleaseDAO {

    @PersistenceContext
    private EntityManager em;

    public ReleaseDAO(EntityManager em) {
        this.em = em;
    }

    public Release create(Release release) {
        em.persist(release);
        return release;
    }

    public Release update(Release release) {
        em.merge(release);
        return release;
    }

    public Release delete(Release release) {
        em.remove(em.contains(release)? release : em.merge(release));
        return release;
    }

    public Optional<Release> findById(Long id) {
        return Optional.ofNullable(em.find(Release.class, id));
    }

    public List<Release> findAll() {
        return em.createQuery("SELECT r FROM Release r", Release.class).getResultList();
    }

    public Optional<Release> findByIdWithTracks(Long id) {
        return em.createQuery(
                "SELECT r FROM Release r LEFT JOIN FETCH r.tracks WHERE r.id = :id", Release.class)
                .setParameter("id", id)
                .getResultList()
                .stream()
                .findFirst();
    }
}

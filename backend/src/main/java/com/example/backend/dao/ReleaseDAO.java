package com.example.backend.dao;

import com.example.backend.entity.Release;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReleaseDAO {

    @PersistenceContext
    private EntityManager em;

    public ReleaseDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Release release) {
        em.persist(release);
    }

    public void update(Release release) {
        em.merge(release);
    }

    public void delete(Release release) {
        em.remove(em.contains(release)? release : em.merge(release));
    }

    public Release findById(Long id) {
        return em.find(Release.class, id);
    }

    public List<Release> findAll() {
        return em.createQuery("SELECT r FROM Release r", Release.class).getResultList();
    }

    public Release findByIdWithTracks(Long id) {
        return em.createQuery(
                "SELECT r FROM Release r LEFT JOIN FETCH r.tracks WHERE r.id = :id", Release.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}

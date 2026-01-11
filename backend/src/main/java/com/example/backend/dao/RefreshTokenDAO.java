package com.example.backend.dao;

import com.example.backend.entity.RefreshToken;
import com.example.backend.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class RefreshTokenDAO {

    @PersistenceContext
    private EntityManager em;

    public RefreshTokenDAO(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void create(RefreshToken refreshToken) {
        em.persist(refreshToken);
    }

    @Transactional
    public void delete(RefreshToken refreshToken) {
        em.remove(em.contains(refreshToken)? refreshToken : em.merge(refreshToken));
    }

    @Transactional(readOnly = true)
    public RefreshToken findById(Long id) {
        return em.find(RefreshToken.class, id);
    }

    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByToken(String token) {
        return em.createQuery("SELECT rt FROM RefreshToken rt JOIN FETCH rt.user WHERE rt.token = :token", RefreshToken.class)
                .setParameter("token", token)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Transactional
    public void deleteByUser(User user) {
        em.createQuery("DELETE FROM RefreshToken rt WHERE rt.user = :user")
                .setParameter("user", user)
                .executeUpdate();
    }

}

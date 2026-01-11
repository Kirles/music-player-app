package com.example.backend.service;

import com.example.backend.dao.ReleaseDAO;
import com.example.backend.entity.Release;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ReleaseServiceImpl implements ReleaseService {

    private final ReleaseDAO releaseDao;

    @Autowired
    public ReleaseServiceImpl(ReleaseDAO releaseDao) {
        this.releaseDao = releaseDao;
    }

    public void createRelease(Release release) {
        releaseDao.create(release);
    }

    public void updateRelease(Release release) {
        releaseDao.update(release);
    }

    public void deleteRelease(Release release) {
        releaseDao.delete(release);
    }

    @Transactional(readOnly = true)
    public Release getReleaseById(Long id) {
        return releaseDao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Release> getAllReleases() {
        return releaseDao.findAll();
    }

    @Transactional(readOnly = true)
    public Release getReleaseByIdWithTracks(Long id) {
        return releaseDao.findByIdWithTracks(id);
    }
}

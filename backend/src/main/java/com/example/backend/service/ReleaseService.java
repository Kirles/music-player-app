package com.example.backend.service;

import com.example.backend.entity.Release;

import java.util.List;

public interface ReleaseService {

    void createRelease(Release release);

    void updateRelease(Release release);

    void deleteRelease(Release release);

    Release getReleaseById(Long id);

    List<Release> getAllReleases();

    Release getReleaseByIdWithTracks(Long id);

}

package com.example.backend.dto;

import com.example.backend.entity.ReleaseType;

import java.time.LocalDate;
import java.util.List;

public class CreateReleaseRequest {

    private String title;

    private ReleaseType releaseType;

    private LocalDate releaseDate;

    private Long artistId;

    private List<TrackDTO> tracks;

    public CreateReleaseRequest() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ReleaseType getReleaseType() {
        return releaseType;
    }

    public void setReleaseType(ReleaseType releaseType) {
        this.releaseType = releaseType;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Long getArtistId() {
        return artistId;
    }

    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}

package com.example.backend.dto;

public class PlaylistDTO {

    private Long id;

    private String title;

    private UserDTO user;

    private Integer totalTracks;

    public PlaylistDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDTO getUser() {;
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Integer getTotalTracks() {
        return totalTracks;
    }

    public void setTotalTracks(Integer totalTracks) {
        this.totalTracks = totalTracks;
    }
}

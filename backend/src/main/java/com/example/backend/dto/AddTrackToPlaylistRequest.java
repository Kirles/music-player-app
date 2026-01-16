package com.example.backend.dto;

import org.antlr.v4.runtime.misc.NotNull;

public class AddTrackToPlaylistRequest {
    @NotNull
    private Long trackId;

    public AddTrackToPlaylistRequest() {}

    public Long getTrackId() {
        return trackId;
    }

    public void setTrackId(Long trackId) {
        this.trackId = trackId;
    }
}

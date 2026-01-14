package com.example.backend.controller;

import com.example.backend.dto.TrackDTO;
import com.example.backend.service.TrackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/artist/tracks")
public class TrackController {

    private final TrackService trackService;

    public TrackController(TrackService trackService){
        this.trackService = trackService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackDTO> getTrackById(@PathVariable Long id) {
        return ResponseEntity.ok(trackService.getTrackById(id));
    }

}

package com.example.backend.controller;

import com.example.backend.dto.ArtistDTO;
import com.example.backend.service.ArtistService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/artists")
public class ArtistController {

    private final ArtistService artistService;

    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtistDTO> getArtistById(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @PostMapping
    public ResponseEntity<ArtistDTO> createArtist(@RequestBody ArtistDTO artistDTO) {
        return ResponseEntity.ok(artistService.createArtist(artistDTO));
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteArtist(@RequestBody ArtistDTO artistDTO) {
        artistService.deleteArtist(artistDTO);
        return HttpStatus.OK;
    }

    @GetMapping
    public ResponseEntity<List<ArtistDTO>> getArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

}

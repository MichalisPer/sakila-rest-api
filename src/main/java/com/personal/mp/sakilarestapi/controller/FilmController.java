package com.personal.mp.sakilarestapi.controller;

import com.personal.mp.sakilarestapi.dto.FilmDto;
import com.personal.mp.sakilarestapi.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public ResponseEntity<List<FilmDto>> getAllFilms() {
        List<FilmDto> films = filmService.getAllFilms();
        return ResponseEntity.ok(films);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable int id) {
        FilmDto film = filmService.getFilmById(id);
        if (film != null) {
            return ResponseEntity.ok(film);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<FilmDto> createFilm(@RequestBody FilmDto film) {
        FilmDto savedFilm = filmService.saveFilm(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFilm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmDto> updateFilm(@PathVariable int id, @RequestBody FilmDto filmDetails) {
        FilmDto film = filmService.getFilmById(id);
        if (film != null) {
            FilmDto updatedFilm = filmService.updateFilm(id, filmDetails);
            return ResponseEntity.ok(updatedFilm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable int id) {
        if (filmService.deleteFilm(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

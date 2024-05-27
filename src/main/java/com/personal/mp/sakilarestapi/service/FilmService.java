package com.personal.mp.sakilarestapi.service;

import com.personal.mp.sakilarestapi.dto.FilmDto;
import com.personal.mp.sakilarestapi.exception.ResourceNotFoundException;
import com.personal.mp.sakilarestapi.mapper.FilmMapper;
import com.personal.mp.sakilarestapi.model.Film;
import com.personal.mp.sakilarestapi.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService {

    private FilmRepository filmRepository;

    private FilmMapper filmMapper;

    @Autowired
    public FilmService(FilmRepository filmRepository, FilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    public List<FilmDto> getAllFilms() {
        List<Film> films = filmRepository.findAll();
        List<FilmDto> results = new ArrayList<>();
        for (Film film : films) {
            results.add(filmMapper.toDTO(film));
        }
        return results;
    }

    public FilmDto getFilmById(int filmId) {
        Film film = filmRepository.findById(filmId).orElse(null);
        return filmMapper.toDTO(film);
    }

    public FilmDto saveFilm(FilmDto filmDto) {
        Film film = filmMapper.toEntity(filmDto);
        return filmMapper.toDTO(filmRepository.save(film));
    }

    public FilmDto updateFilm(int filmId, FilmDto filmDetails) {
        return filmRepository.findById(filmId).map(film -> {
            film.setLength(filmDetails.getLength());
            film.setRating(filmDetails.getRating());
            film.setTitle(filmDetails.getTitle());
            film.setDescription(filmDetails.getDescription());
            film.setReleaseYear(filmDetails.getReleaseYear());
            film.setRentalDuration(filmDetails.getRentalDuration());
            film.setRentalRate(filmDetails.getRentalRate());
            film.setSpecialFeatures(filmDetails.getSpecialFeatures());
            film.setReplacementCost(filmDetails.getReplacementCost());
            film.setLastUpdate(filmDetails.getLastUpdate());

            return filmMapper.toDTO(filmRepository.save(film));
        }).orElseThrow(() -> new ResourceNotFoundException("Film not found"));
    }

    public boolean deleteFilm(int filmId) {
        return filmRepository.findById(filmId).map(film -> {
            filmRepository.delete(film);
            return true;
        }).orElse(false);
    }

}

package com.personal.mp.sakilarestapi.mapper;

import com.personal.mp.sakilarestapi.dto.FilmDto;
import com.personal.mp.sakilarestapi.model.Film;
import org.springframework.stereotype.Component;

@Component
public class FilmMapper {

    public FilmDto toDTO(Film film) {
        if (film == null) {
            return null;
        }

        return new FilmDto(film.getId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate());
    }

    public Film toEntity(FilmDto filmDto) {
        if (filmDto == null) {
            return null;
        }
        Film film = new Film();
        film.setDescription(film.getDescription());
        film.setLength(filmDto.getLength());
        film.setRating(filmDto.getRating());
        film.setTitle(filmDto.getTitle());
        film.setReleaseYear(filmDto.getReleaseYear());
        film.setRentalDuration(filmDto.getRentalDuration());
        film.setRentalRate(filmDto.getRentalRate());
        film.setReplacementCost(filmDto.getReplacementCost());
        film.setSpecialFeatures(filmDto.getSpecialFeatures());
        film.setLastUpdate(filmDto.getLastUpdate());
        return film;
    }
}

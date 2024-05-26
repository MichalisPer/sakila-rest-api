package com.personal.mp.sakilarestapi.repository;

import com.personal.mp.sakilarestapi.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {
}
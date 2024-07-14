package com.challenge.literAlura.repository;

import com.challenge.literAlura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query("SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear IS NULL OR a.deathYear > :year)")
    List<Author> findAuthorsAliveInYear(Integer year);
}

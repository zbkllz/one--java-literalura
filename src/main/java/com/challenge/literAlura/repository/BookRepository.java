package com.challenge.literAlura.repository;

import com.challenge.literAlura.model.Book;
import com.challenge.literAlura.model.Language;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.language = :language")
    Page<Book> findBooksByLanguage(Language language, Pageable pageable);
}

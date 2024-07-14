package com.challenge.literAlura.controller;

import com.challenge.literAlura.literalura.Menu;
import com.challenge.literAlura.literalura.ScreenReset;
import com.challenge.literAlura.model.Author;
import com.challenge.literAlura.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Scanner;

public class AuthorArchive {
    private final Scanner scanner = new Scanner(System.in);
    private final AuthorRepository authorRepository;
    private static String authorInput = "";
    private static Integer pageNumber = 1;

    public AuthorArchive(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public void load() {
        while (!authorInput.equals("0")) {
            Pageable pageable = PageRequest.of(pageNumber - 1, 10);
            Page<Author> page = authorRepository.findAll(pageable);
            page.forEach(Author::printAuthor);
            Menu.authorMenu();
            Menu.authorMenuInfo(pageNumber, page);
            Menu.askOption();
            authorInput = scanner.nextLine();
            ScreenReset.clear();
            AuthorArchiveOptions authorArchiveOptions = new AuthorArchiveOptions(authorRepository, authorInput, page, pageNumber);
            authorArchiveOptions.checkOption();
        }

        authorInput = "";
    }

    public static void setPageNumber(Integer pageNumber) {
        AuthorArchive.pageNumber = pageNumber;
    }

    public static void setAuthorInput(String authorInput) {
        AuthorArchive.authorInput = authorInput;
    }
}

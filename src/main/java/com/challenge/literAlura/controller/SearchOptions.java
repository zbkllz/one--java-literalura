package com.challenge.literAlura.controller;

import com.challenge.literAlura.literalura.MainEntry;
import com.challenge.literAlura.literalura.Menu;
import com.challenge.literAlura.literalura.ScreenReset;
import com.challenge.literAlura.model.Book;
import com.challenge.literAlura.repository.BookRepository;
import com.challenge.literAlura.service.BookService;
import com.challenge.literAlura.service.Mapper;
import com.challenge.literAlura.literalura.CheckNullResponseBody;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.Scanner;

public class SearchOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService = new BookService();
    private final Mapper mapper = new Mapper();
    private final String searchMenuOption;
    private final BookRepository bookRepository;
    private final List<Book> books;

    public SearchOptions(String searchMenuOption, BookRepository bookRepository, List<Book> books) {
        this.searchMenuOption = searchMenuOption;
        this.bookRepository = bookRepository;
        this.books = books;
    }

    public void checkOption() {
        switch (searchMenuOption) {
            case "1":
                ScreenReset.clear();
                Menu.saving();

                try {
                    bookRepository.saveAll(books);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                MainEntry.setUserInput("");
                break;
            case "2":
                ScreenReset.clear();
                Menu.askId();
                String bookId = scanner.nextLine();
                ScreenReset.clear();
                Menu.connecting();
                String responseBody = bookService
                        .getResponseBody("https://gutendex.com/books/" + bookId + "/");
                CheckNullResponseBody.check(responseBody, mapper, bookRepository);
                MainEntry.setUserInput("");
                break;
            case "0":
                ScreenReset.clear();
                MainEntry.setUserInput("");
                break;
            default:
                ScreenReset.clear();
                Menu.invalidOption();
                break;
        }
    }
}

package com.challenge.literAlura.controller;

import com.challenge.literAlura.literalura.MainEntry;
import com.challenge.literAlura.literalura.Menu;
import com.challenge.literAlura.literalura.ScreenReset;
import com.challenge.literAlura.model.Book;
import com.challenge.literAlura.model.DataIndex;
import com.challenge.literAlura.repository.BookRepository;

import java.util.List;
import java.util.Scanner;

public class Search {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private final BookRepository bookRepository;

    public Search(BookRepository bookRepository) {
        this.dataIndex = MainEntry.getDataIndex();
        this.bookRepository = bookRepository;
    }

    public void load() {
        if (!dataIndex.books().isEmpty()) {
            ScreenReset.clear();
            List<Book> books = dataIndex.books().stream().map(Book::new).toList();
            books.forEach(Book::printBook);
            Menu.searchMenu();
            String searchMenuOption = scanner.nextLine();
            SearchOptions searchOptions = new SearchOptions(searchMenuOption, bookRepository, books);
            searchOptions.checkOption();
        } else {
            ScreenReset.clear();
            Menu.notFound();
        }
    }
}
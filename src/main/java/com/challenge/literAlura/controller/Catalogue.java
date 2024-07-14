package com.challenge.literAlura.controller;

import com.challenge.literAlura.literalura.MainEntry;
import com.challenge.literAlura.literalura.Menu;
import com.challenge.literAlura.literalura.ScreenReset;
import com.challenge.literAlura.model.DataIndex;
import com.challenge.literAlura.repository.BookRepository;
import com.challenge.literAlura.model.Book;

import java.util.List;
import java.util.Scanner;

public class Catalogue {
    private final Scanner scanner = new Scanner(System.in);
    private final DataIndex dataIndex;
    private final BookRepository bookRepository;
    private Integer apiPageNumber;
    private String apiPage;

    public Catalogue(DataIndex dataIndex, BookRepository bookRepository) {
        this.dataIndex = dataIndex;
        this.bookRepository = bookRepository;
        this.apiPageNumber = MainEntry.getApiPageNumber();
        this.apiPage = MainEntry.getApiPage();
    }

    public void load() {
        if (this.dataIndex.invalidPage() == null) {
            List<Book> books = this.dataIndex.books().stream().map(Book::new).toList();
            books.forEach(Book::printBook);
            Menu.pageAndLang();
            Menu.catalogueMenu();
            String userOption = scanner.nextLine();
            ScreenReset.clear();
            CatalogueOptions catalogueOptions = new CatalogueOptions(userOption, books, bookRepository);
            catalogueOptions.checkOption();
        } else {
            ScreenReset.clear();
            Menu.pageNotFound();
            Menu.backToCatalogue();
            apiPageNumber = 1;
            apiPage = String.valueOf(apiPageNumber);
            MainEntry.setApiPageNumber(apiPageNumber);
            MainEntry.setApiPage(apiPage);
        }
    }
}

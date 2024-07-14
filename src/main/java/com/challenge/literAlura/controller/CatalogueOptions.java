package com.challenge.literAlura.controller;

import com.challenge.literAlura.literalura.CheckNullResponseBody;
import com.challenge.literAlura.literalura.MainEntry;
import com.challenge.literAlura.literalura.ScreenReset;
import com.challenge.literAlura.literalura.Menu;
import com.challenge.literAlura.model.Book;
import com.challenge.literAlura.service.BookService;
import com.challenge.literAlura.model.DataIndex;
import com.challenge.literAlura.repository.BookRepository;
import com.challenge.literAlura.service.Mapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CatalogueOptions {
    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService = new BookService();
    private final Mapper mapper = new Mapper();
    private final DataIndex dataIndex;
    private final String userOption;
    private final List<Book> books;
    private final BookRepository bookRepository;
    private String apiPage;
    private Integer apiPageNumber;


    public CatalogueOptions(String userOption, List<Book> books, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.dataIndex = MainEntry.getDataIndex();
        this.userOption = userOption;
        this.books = books;
        this.apiPage = MainEntry.getApiPage();
        this.apiPageNumber = MainEntry.getApiPageNumber();
    }

    public void checkOption() {
        switch (this.userOption) {
            case "1":
                if (dataIndex.previousPage() != null) {
                    apiPageNumber--;
                    apiPage = String.valueOf(apiPageNumber);
                    MainEntry.setApiPageNumber(apiPageNumber);
                    MainEntry.setApiPage(apiPage);
                }
                break;
            case "2":
                if (dataIndex.nextPage() != null) {
                    apiPageNumber++;
                    apiPage = String.valueOf(apiPageNumber);
                    MainEntry.setApiPageNumber(apiPageNumber);
                    MainEntry.setApiPage(apiPage);
                }
                break;
            case "3":
                Menu.askPage();

                try {
                    apiPageNumber = scanner.nextInt();
                    scanner.nextLine();
                    apiPage = String.valueOf(apiPageNumber).trim();
                    MainEntry.setApiPageNumber(apiPageNumber);
                    MainEntry.setApiPage(apiPage);
                } catch (InputMismatchException e) {
                    System.out.println("A busca deve ser por número.");
                    break;
                }
                break;
            case "4":
                ScreenReset.clear();
                Menu.saving();

                try {
                    bookRepository.saveAll(books);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                break;
            case "5":
                Menu.askId();
                String bookId = scanner.nextLine();
                ScreenReset.clear();
                Menu.connecting();
                String responseBody = bookService
                        .getResponseBody("https://gutendex.com/books/" + bookId + "/");
                CheckNullResponseBody.check(responseBody, mapper, bookRepository);
                break;
            case "6":
                Menu.askOption();
                Menu.askLanguage();
                String langOption = scanner.nextLine();
                ScreenReset.clear();

                switch (langOption) {
                    case "1":
                        MainEntry.setLangEn("en");
                        MainEntry.setLangPt("");
                        break;
                    case "2":
                        MainEntry.setLangEn("");
                        MainEntry.setLangPt("pt");
                        break;
                    case "3":
                        MainEntry.setLangEn("en");
                        MainEntry.setLangPt("pt");
                        break;
                    case "0":
                        Menu.backToCatalogue();
                        break;
                    default:
                        MainEntry.setLangEn("en");
                        MainEntry.setLangPt("pt");
                        Menu.invalidOption();
                        break;
                }

                break;
            case "0":
                MainEntry.setUserInput("");
                break;
            default:
                Menu.invalidOption();
                break;
        }
    }
}

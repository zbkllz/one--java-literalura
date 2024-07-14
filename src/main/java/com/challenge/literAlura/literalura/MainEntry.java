package com.challenge.literAlura.literalura;

import com.challenge.literAlura.controller.Archive;
import com.challenge.literAlura.controller.AuthorArchive;
import com.challenge.literAlura.controller.Catalogue;
import com.challenge.literAlura.controller.Search;
import com.challenge.literAlura.model.DataIndex;
import com.challenge.literAlura.repository.AuthorRepository;
import com.challenge.literAlura.repository.BookRepository;
import com.challenge.literAlura.service.BookService;
import com.challenge.literAlura.service.Mapper;
import lombok.Getter;

import java.util.Scanner;

public class MainEntry {

    private final Scanner scanner = new Scanner(System.in);
    private final BookService bookService = new BookService();
    private final Mapper mapper = new Mapper();
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    @Getter
    private static DataIndex dataIndex;
    @Getter
    private static Integer apiPageNumber = 1;
    @Getter
    private static String langPt = "pt";
    private static final String langComma = ",";
    @Getter
    private static String langEn = "en";
    @Getter
    private static String apiPage = String.valueOf(apiPageNumber);
    private static String userInput = "";
    private String responseBody;

    public MainEntry(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void init() {
        ScreenReset.clear();
        boolean exitLoop = false;

        while (!exitLoop) {

            if (userInput.isEmpty()) {
                Menu.mainMenu();
                userInput = scanner.nextLine();
                ScreenReset.clear();
            }

            switch (userInput) {
                case "1":
                    Menu.connecting();
                    responseBody = bookService.getResponseBody(
                            "https://gutendex.com/books/?languages=" +
                            langPt + langComma + langEn +
                            "&page=" + apiPage
                    );

                    if (responseBody != null) {
                        dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                        Catalogue catalogue = new Catalogue(dataIndex, bookRepository);
                        catalogue.load();
                    }

                    break;
                case "2":
                    Menu.askName();
                    String apiSearchName = scanner.nextLine().replace(" ", "%20").toLowerCase();
                    ScreenReset.clear();
                    Menu.connecting();
                    responseBody = bookService
                            .getResponseBody("https://gutendex.com/books/?languages=pt,en&search=" + apiSearchName);
                    dataIndex = mapper.getClassFromJson(responseBody, DataIndex.class);
                    Search search = new Search(bookRepository);
                    search.load();
                    break;
                case "3":
                    Archive archive = new Archive(bookRepository);
                    archive.load();
                    userInput = "";
                    break;
                case "4":
                    AuthorArchive authorArchive = new AuthorArchive(authorRepository);
                    authorArchive.load();
                    userInput = "";
                    break;
                case "0":
                    Menu.exit();
                    exitLoop = true;
                    break;
                default:
                    Menu.invalidOption();
                    userInput = "";
                    break;
            }
        }
    }

    public static void setLangPt(String langPt) {
        MainEntry.langPt = langPt;
    }

    public static void setLangEn(String langEn) {
        MainEntry.langEn = langEn;
    }

    public static void setApiPage(String apiPage) {
        MainEntry.apiPage = apiPage;
    }

    public static void setApiPageNumber(Integer apiPageNumber) {
        MainEntry.apiPageNumber = apiPageNumber;
    }

    public static void setUserInput(String userInput) {
        MainEntry.userInput = userInput;
    }
}


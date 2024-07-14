package com.challenge.literAlura.controller;

import com.challenge.literAlura.literalura.Menu;
import com.challenge.literAlura.literalura.ScreenReset;
import com.challenge.literAlura.model.Book;
import org.springframework.data.domain.Page;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ArchiveOptions {
    private final Scanner scanner = new Scanner(System.in);
    private String archiveInput;
    private Integer pageNumber;
    private final Page<Book> page;

    public ArchiveOptions(String archiveInput, Page<Book> page, Integer pageNumber) {
        this.archiveInput = archiveInput;
        this.page = page;
        this.pageNumber = pageNumber;
    }

    public void checkOption() {
        switch (archiveInput) {
            case "1":
                if (!page.isFirst()) {
                    pageNumber--;
                    Archive.setPageNumber(pageNumber);
                }
                break;
            case "2":
                if (!page.isLast()) {
                    pageNumber++;
                    Archive.setPageNumber(pageNumber);
                }
                break;
            case "3":
                boolean checkOption = false;

                while (!checkOption) {
                    Menu.askPage();

                    try {
                        pageNumber = scanner.nextInt();
                        scanner.nextLine();

                        if (pageNumber >= 1 && pageNumber <= page.getTotalPages()) {
                            Archive.setPageNumber(pageNumber);
                            checkOption = true;
                            ScreenReset.clear();
                        } else {
                            ScreenReset.clear();
                            Menu.invalidPage(page);
                        }

                    } catch (InputMismatchException e) {
                        Menu.invalidPage(page);
                        break;
                    }

                }
                break;
            case "4":
                Menu.askOption();
                Menu.askLanguage();
                String langOption = scanner.nextLine();
                ScreenReset.clear();

                switch (langOption) {
                    case "1":
                        Archive.setLangOption("en");
                        break;
                    case "2":
                        Archive.setLangOption("pt");
                        break;
                    case "3":
                        Archive.setLangOption("all");
                        break;
                    case "0":
                        Menu.backToCatalogue();
                        break;
                    default:
                        Menu.invalidOption();
                        break;
                }

                break;
            case "0":
                Archive.setPageNumber(1);
                archiveInput = "0";
                break;
            default:
                Menu.invalidOption();
                break;
        }
    }

}

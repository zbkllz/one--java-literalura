package com.challenge.literAlura.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;


@Entity
@Table(name = "livros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private Integer idBook;
    @Getter
    @Column(nullable = false, length = 510)
    private String title;
    private Boolean copyright;
    private String copyrightText;
    private Integer downloadCount;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<Theme> themes;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Author> authors;
    @Getter
    @Enumerated(EnumType.STRING)
    private Language language;

    protected Book() {}

    public Book(BookData book) {
        this.idBook = book.idBook();
        this.title = book.title();
        this.copyright = book.copyright();
        this.copyrightText = this.copyright ? "Sim" : "Não";
        this.downloadCount = book.downloadCount();
        this.themes = book.subjects().stream().map(Theme::new).toList();
        this.themes.forEach(theme -> theme.setBook(this));
        this.authors = book.authors().stream().map(Author::new).toList();
        this.authors.forEach(author -> author.setBooks(this));
        this.language = Language.fromString(book.languages().get(0).trim());
    }

    public void printBook() {
        System.out.println("**** Livro: " + title + " ****");
        System.out.println("--- Id: " + idBook);
        if (language == Language.ENGLISH) {
            System.out.println("--- Idioma: Inglês");
        } else if (language == Language.PORTUGUESE) {
            System.out.println("--- Idioma: Português");
        }
        if (authors != null) {
            System.out.println("--- Autores: ");
            authors.forEach(author -> System.out.println("+ " + author.getName()));
        }
        System.out.println("--- Possui copyright? " + copyrightText);
        System.out.println("--- Número de downloads: " + downloadCount);
        if (themes != null) {
            System.out.println("--- Temas: ");
            themes.forEach(theme -> System.out.println("+ " + theme.getName()));
        }
        System.out.println();
    }
}

package com.challenge.literAlura.literalura;

import com.challenge.literAlura.controller.Archive;
import com.challenge.literAlura.model.*;
import org.springframework.data.domain.Page;
import java.util.List;


public class Menu {
    private static final String mainMenu = """
            
            Seja bem-vindo(a) ao LiterAlura!
            
            Escolha uma opção:
            
            1 - Exibir catálogo
            2 - Buscar título/autor no catálogo
            3 - Consultar livros salvos
            4 - Consultar autores salvos
            0 - Sair
            """;
    private static final String searchMenu = """
           Menu da Busca por Título/Autor:
            
            1 - Salvar todos os livros desta página
            2 - Salvar livro por id
            0 - Voltar ao menu principal
            """;
    private static final String catalogueMenu = """
            Menu do Catálogo:
            
            1 - Página anterior
            2 - Próxima página
            3 - Buscar página no catálogo
            4 - Salvar todos os livros desta página
            5 - Salvar livro por id
            6 - Filtrar livros por idioma
            0 - Voltar ao menu principal
            """;
    private static final String archiveMenu = """
           Livros salvos:
           
            1 - Página anterior
            2 - Pŕoxima página
            3 - Buscar página
            4 - Filtrar livros por idioma
            0 - Voltar ao menu principal
           """;
    private static final String authorMenu = """
            Autores salvos:
            
            1 - Página anterior
            2 - Pŕoxima página
            3 - Buscar página 
            4 - Filtrar autores vivos em determinado ano
            0 - Voltar ao menu principal
            """;

    public static void authorMenu() { System.out.println(authorMenu); }

    public static void authorMenuInfo (int pageNumber, Page<Author> page) {
        System.out.println("Exibindo página " + pageNumber + " de " + page.getTotalPages());
        System.out.println("Autores nesta página: " + page.getNumberOfElements());
        System.out.println("Total de Autores Armazenados: " + page.getTotalElements() + "\n");
    }


    // MainEntry
    public static void mainMenu() {
        System.out.println(mainMenu);
    }
    // MainEntry
    public static void searchMenu() {
        System.out.println(searchMenu);
    }
    // MainEntry
    public static void archiveMenu() { System.out.println(archiveMenu); }
    // MainEntry
    public static void askName() { System.out.println("Digite um nome para busca: "); }
    // MainEntry
    public static void exit() { System.out.println("Até a próxima :) ...\n"); }
    // MainEntry
    public static void archiveMenuInfo (int pageNumber, Page<Book> page, List<Book> books) {
        System.out.println("Exibindo página " + pageNumber + " de " + page.getTotalPages());
        System.out.println("Livros nesta página: " + page.getNumberOfElements());
        System.out.println("Total de Livros Armazenados: " + page.getTotalElements());
        System.out.println("Exibindo os idiomas: ");
        List<Book> booksEn = books.stream().filter(book -> book.getLanguage().equals(Language.ENGLISH)).toList();
        List<Book> booksPt = books.stream().filter(book -> book.getLanguage().equals(Language.PORTUGUESE)).toList();

        if (Archive.getLangOption().equals("en")) {
            System.out.println("+ Inglês: " + booksEn.size() + " livros\n");
        } else if (Archive.getLangOption().equals("pt")) {
            System.out.println("+ Português: " + booksPt.size() + " livros\n");
        } else {
            System.out.println("+ Inglês: " + booksEn.size() + " livros");
            System.out.println("+ Português: " + booksPt.size() + " livros\n");
        }
    }

    // ArchiveOptions
    public static <T> void invalidPage (Page<T> page) {
        System.out.println("Digite uma página entre 1 e " + page.getTotalPages() + ".");
    }

    // Catalogue
    public static void pageNotFound() { System.out.println("Página: " + MainEntry.getApiPage() + " não encontrada"); }
    // Catalogue
    public static void pageAndLang() {
        System.out.println("***** Página: " + MainEntry.getApiPage() + " *****");
        System.out.println("* Exibindo os idiomas *");
        if (MainEntry.getLangEn().equals("en") && MainEntry.getLangPt().isEmpty()) {
            System.out.println("+ Inglês\n");
        } else if (MainEntry.getLangPt().equals("pt") && MainEntry.getLangEn().isEmpty()) {
            System.out.println("+ Português\n");
        } else {
            System.out.println("+ Inglês");
            System.out.println("+ Português\n");
        }
    }

    // CatalogueOptions
    public static void askId() { System.out.println("Digite o id do livro: "); }
    // CatalogueOptions
    public static void askLanguage() {
        System.out.println("\n1 - Inglês");
        System.out.println("2 - Português");
        System.out.println("3 - Inglês e Português");
        System.out.println("0 - Voltar\n");
    }

    // CatalogueOptions
    public static void saving() { System.out.println("Salvando...\n"); }
    // CatalogueOptions
    public static void saved() { System.out.println("Salvo com sucesso!\n"); }
    // CatalogueOptions
    public static void alreadySaved() { System.out.println("Livro/Livros já armazenado(s) anteriormente!\n"); }

    // MainEntry and Catalogue
    public static void catalogueMenu() { System.out.println(catalogueMenu); }
    // Catalogue and CatalogueOptions
    public static void backToCatalogue() { System.out.println("Voltando ao catálogo...\n"); }
    // MainEntry and CatalogueOptions
    public static void askOption() { System.out.println("Selecione uma opção: "); }
    // MainEntry and CatalogueOptions
    public static void connecting() { System.out.println("Estabelecendo conexão...\n"); }
    // MainEntry and CatalogueOptions
    public static void invalidOption() { System.out.println("Digite uma opção válida."); }
    // MainEntry and CatalogueOptions
    public static void notFound() { System.out.println("Livro/Autor não encontrado."); }
    // MainEntry and CatalogueOptions
    public static void askPage() { System.out.println("Digite o número da página para busca: "); }

}

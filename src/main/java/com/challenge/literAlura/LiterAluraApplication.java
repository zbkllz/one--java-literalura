package com.challenge.literAlura;

import com.challenge.literAlura.literalura.MainEntry;
import com.challenge.literAlura.repository.AuthorRepository;
import com.challenge.literAlura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		try {
			MainEntry mainEntry = new MainEntry(bookRepository, authorRepository);
			mainEntry.init();
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
}

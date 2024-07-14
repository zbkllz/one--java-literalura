package com.challenge.literAlura.literalura;

import com.challenge.literAlura.model.Book;
import com.challenge.literAlura.model.BookData;
import com.challenge.literAlura.repository.BookRepository;
import com.challenge.literAlura.service.Mapper;
import org.springframework.dao.DataIntegrityViolationException;

public class CheckNullResponseBody {
    public static void check(String responseBody, Mapper mapper, BookRepository bookRepository) {
        if (responseBody != null) {
            BookData bookDataById = mapper.getClassFromJson(responseBody, BookData.class);
            ScreenReset.clear();

            if (bookDataById.invalidPage() == null) {
                Book bookById = new Book(bookDataById);
                Menu.saving();

                try {
                    bookRepository.save(bookById);
                    Menu.saved();
                } catch (DataIntegrityViolationException e) {
                    Menu.alreadySaved();
                }

                bookById.printBook();
            } else {
                Menu.notFound();
            }

        }
    }
}

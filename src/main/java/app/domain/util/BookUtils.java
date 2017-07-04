package app.domain.util;


import app.domain.entity.Author;
import app.domain.entity.Book;

import java.util.ArrayList;

public class BookUtils {

    public static void mapBookToAuthor(Book book, Author author) {
        if (book.getAuthors() == null) {
            book.setAuthors(new ArrayList<Author>());
        }
        book.getAuthors().add(author);

        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<Book>());
        }
        author.getBooks().add(book);
    }
}

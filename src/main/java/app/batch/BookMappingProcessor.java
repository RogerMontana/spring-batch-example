package app.batch;



import app.domain.entity.Author;
import app.domain.entity.Book;
import app.domain.service.BooksService;
import app.domain.util.BookUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Anton German
 * @since 09 June 2016
 */

public class BookMappingProcessor implements ItemProcessor<FieldSet,Book> {

    @Autowired
    private BooksService booksService;

    @Override
    public Book process(FieldSet fields) throws Exception {
        final Book book = booksService.getBookByISBN(fields.readString("isbn"));
        final Author author = booksService.getAuthorByName(fields.readString("name"));
        BookUtils.mapBookToAuthor(book, author);
        return book;
    }
}


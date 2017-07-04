package app.domain.service;


import app.domain.entity.Author;
import app.domain.entity.Book;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Service
public class BooksService {
    @PersistenceContext
    private EntityManager em;

    @Transactional(Transactional.TxType.REQUIRED)
    public Book getBookByISBN(String isbn) {
        final TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Transactional(Transactional.TxType.REQUIRED)
    public Author getAuthorByName(String name) {
        final TypedQuery<Author> query = em.createQuery("SELECT a FROM Author a WHERE a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

}

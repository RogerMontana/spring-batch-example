package app.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.Collection;

@Entity
@Table(name = "BOOK")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Book {
    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private long id;

    @XmlElement
    @Column(name = "BOOK_TITLE")
    private String title;

    @XmlElement
    @Column(name = "BOOK_ISBN")
    private String isbn;

    @XmlElement
    @Enumerated(EnumType.STRING)
    @Column(name = "BOOK_LANGUAGE")
    private Language language;

    @XmlElement(name = "author")
    @XmlElementWrapper(name = "authors")
    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private Collection<Author> authors;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Collection<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Collection<Author> authors) {
        this.authors = authors;
    }

}

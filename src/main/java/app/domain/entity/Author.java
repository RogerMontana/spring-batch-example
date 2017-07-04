package app.domain.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;

@Entity
@Table(name = "AUTHOR")
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class Author {
    @Id
    @GeneratedValue
    @Column(name = "AUTHOR_ID")
    private long id;

    @XmlElement
    @Column(name = "AUTHOR_NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "BOOK_AUTHOR",
            joinColumns = @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "AUTHOR_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID", referencedColumnName = "BOOK_ID"))
    private Collection<Book> books;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Book> getBooks() {
        return books;
    }

    public void setBooks(Collection<Book> books) {
        this.books = books;
    }

}

package code.entities;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    private Long id;
    private String title;
    private String description;
    private String author;
    private String isbn;
    private Integer print_year;
    private boolean read_already;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min = 1, max = 30)
    @NotNull
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Size(min = 3, max = 30)
    @NotNull
    @Column(name = "AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Pattern(regexp = "[\\d-]{10,20}")
    @Column(name = "ISBN")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Range(min = 1900, max = 2020)
    @Column(name = "PRINT_YEAR")
    public Integer getPrint_year() {
        return print_year;
    }

    public void setPrint_year(Integer print_year) {
        this.print_year = print_year;
    }

    @Column(name = "READ_ALREADY")
    public boolean isRead_already() {
        return read_already;
    }

    public void setRead_already(boolean read_already) {
        this.read_already = read_already;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", print_year=" + print_year +
                ", read_already=" + read_already +
                '}';
    }
}

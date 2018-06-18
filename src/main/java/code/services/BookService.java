package code.services;

import code.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();
    Book findById(Long id);
    void delete(Long id);
    void save(Book book);
}

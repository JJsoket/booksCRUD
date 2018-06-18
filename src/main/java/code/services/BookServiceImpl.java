package code.services;

import code.entities.Book;
import code.repositories.BookRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
@Service("BookService")
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return Lists.newArrayList(bookRepository.findAll());
    }

    @Transactional(readOnly = true)
    public Book findById(Long id) {
        return bookRepository.findOne(id);
    }

    public void delete(Long id) {
        bookRepository.delete(id);
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}

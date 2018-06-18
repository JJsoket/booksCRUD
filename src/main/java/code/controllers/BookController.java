package code.controllers;

import code.entities.Book;
import code.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@RequestMapping("/books")
@Controller
public class BookController {
    private BookService bookService;

    @RequestMapping(method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "books/show";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long id, Model Model) {
        Model.addAttribute("book", bookService.findById(id));
        return "books/edit";
    }

    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update(@Valid Book book, BindingResult bindingResult, Model Model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Model.addAttribute("message", "Update fail");
            Model.addAttribute("book", book);
            return "books/edit";
        }
        Model.asMap().clear();
        redirectAttributes.addFlashAttribute("message", "book was update");
        bookService.save(book);
        return "redirect:/books/" + book.getId();
    }

    @RequestMapping(params = "form", method = RequestMethod.GET)
    public String createForm(Model Model) {
        Book book = new Book();
        Model.addAttribute("book", book);
        return "books/edit";
    }

    @RequestMapping(params = "form", method = RequestMethod.POST)
    public String create(@Valid Book book, BindingResult bindingResult, Model Model, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            Model.addAttribute("message", "create fail");
            Model.addAttribute("book", book);
            return "books/edit";
        }
        Model.asMap().clear();
        redirectAttributes.addFlashAttribute("message", "Book \'" + book.getTitle() + "\' was created");

        bookService.save(book);
        return "redirect:/books/";
    }

    @RequestMapping(value = "/{id}", params = "read")
    public String read(@PathVariable Long id, Model model) {
        Book book = bookService.findById(id);
        if (book != null) {
            book.setRead_already(true);
            bookService.save(book);
        }
        model.addAttribute("message", book != null ? "book was readed" : "No books with that id for reading");
        return book != null ? show(id, model) : "redirect:/books";
    }

    @RequestMapping(value = "/{id}", params = "delete")
    public String delete(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Book book = bookService.findById(id);
        if (book != null) {
            bookService.delete(id);
            redirectAttributes.addFlashAttribute("message", "Book \'" + book.getTitle() + "\' was deleted");
        } else
            redirectAttributes.addFlashAttribute("message", "No books with similar id such for delete");
        return "redirect:/books/";
    }

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}

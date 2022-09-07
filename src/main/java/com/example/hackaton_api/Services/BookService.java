package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Repositories.BookRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

@Service
@AllArgsConstructor
@Getter
@Setter
public class BookService {

    private BookRepository bookRepository;

    public List<Book> getBooks(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        List<Book> list = bookRepository.findAll(pageable)
                .stream().sorted(comparing(Book::getLoansNumber)).toList();
        return list;
    }

    public Book addBook(Book book) {
        book.setStatus(Book.Status.available);
        book.setLoansNumber(0);
        return bookRepository.save(book);
    }

    public Book modifyBook(Book book) {
        if(bookRepository.existsById(book.getIdBook())){
            Book newBook = bookRepository.findById(book.getIdBook()).get();
            newBook.setCategory(book.getCategory());
            newBook.setAuthor(book.getAuthor());
            newBook.setTitle(book.getTitle());
            newBook.setPageNumber(book.getPageNumber());
            return bookRepository.save(newBook);
        }
        else{
            return null;
        }
    }

    public String delete(int id){
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }

    public String multipleDelete(List<Integer> ids){
       // ids.forEach((e) -> bookRepository.deleteById(e));
        for (int i = 0; i < ids.size(); i++) {
            bookRepository.deleteById(ids.get(i));
        }
        return "Books deleted successfully";
    }

    public String loanBook(int id){
        if(bookRepository.existsById(id)){
            Book book = bookRepository.findById(id).get();
            if(book.getStatus() == Book.Status.unavailable){
                book.setStatus(Book.Status.available);
                modifyBook(book);
                return "This book is unavailable";
            }
            if (book.getStatus() == Book.Status.available){
                book.setStatus(Book.Status.unavailable);
                book.setLoansNumber(book.getLoansNumber() + 1);
                modifyBook(book);
                return "This book is available";
            }
        }
        return null;
    }
}

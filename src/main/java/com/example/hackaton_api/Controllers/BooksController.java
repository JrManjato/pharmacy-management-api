package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Services.BookService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Getter
@Setter
@CrossOrigin
public class BooksController {

    private BookService service;

    @GetMapping("/books")
    public List<Book> getAllBooks(@RequestParam(name = "pageNumber") int page,
                                  @RequestParam(name = "pageSize") int pageSize){
        return service.getBooks(page,pageSize);
    }

    @PostMapping("/books")
    public Book addBook(@RequestBody Book book){
        return service.addBook(book);
    }

    @PutMapping("/books")
    public Book modifyBook(@RequestBody Book book){
        return service.modifyBook(book);
    }

    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable int id){
        return service.delete(id);
    }

    @DeleteMapping("/books/{ids}")
    public String deleteBooks(@PathVariable List<Integer> ids){
        return service.multipleDelete(ids);
    }

    @PutMapping("/loan/{id}")
    public String loanBook(@PathVariable int id){
        return service.loanBook(id);
    }
}

    //http://localhost:8080/categories?pageNumber=1&pageSize=10
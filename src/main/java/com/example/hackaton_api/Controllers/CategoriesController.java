package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Models.Category;
import com.example.hackaton_api.Services.BookService;
import com.example.hackaton_api.Services.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@CrossOrigin
public class CategoriesController {
  @Autowired
  private CategoryService service;
  @GetMapping("/categories")
  public List<Category> getAllBooks(@RequestParam(name = "pageNumber") int page,
                                    @RequestParam(name = "pageSize") int pageSize){
    return service.getCategories(page,pageSize);
  }
}

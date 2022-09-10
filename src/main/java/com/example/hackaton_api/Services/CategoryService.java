package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Models.Category;
import com.example.hackaton_api.Repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Getter
@Setter
public class CategoryService {
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(int page, int pageSize){
      Pageable pageable = PageRequest.of(page - 1, pageSize);
      List<Category> list = categoryRepository.findAll(pageable)
              .stream().toList();
      return list;
    }
}

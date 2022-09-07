package com.example.hackaton_api.Repositories;

import com.example.hackaton_api.Models.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

}
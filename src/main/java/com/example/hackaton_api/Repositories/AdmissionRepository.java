package com.example.hackaton_api.Repositories;

import com.example.hackaton_api.Models.Admission;
import com.example.hackaton_api.Models.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
  Admission findByAdmissionName(String name);
}

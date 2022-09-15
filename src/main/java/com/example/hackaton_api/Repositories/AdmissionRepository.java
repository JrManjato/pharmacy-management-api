package com.example.hackaton_api.Repositories;

import com.example.hackaton_api.Models.Admission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
  Admission findByAdmissionName(String name);
}

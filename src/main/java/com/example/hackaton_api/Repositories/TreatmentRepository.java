package com.example.hackaton_api.Repositories;

import com.example.hackaton_api.Models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment,Integer> {
  Treatment findByTreatmentName(String name);
}

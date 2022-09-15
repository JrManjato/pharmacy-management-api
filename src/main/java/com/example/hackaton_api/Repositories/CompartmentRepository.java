package com.example.hackaton_api.Repositories;

import com.example.hackaton_api.Models.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompartmentRepository extends JpaRepository<Compartment,Integer> {
  Compartment findByCompartmentName(String name);
}

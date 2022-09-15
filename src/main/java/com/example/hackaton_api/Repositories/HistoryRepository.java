package com.example.hackaton_api.Repositories;

import com.example.hackaton_api.Models.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryRepository extends JpaRepository<History,Integer> {

}

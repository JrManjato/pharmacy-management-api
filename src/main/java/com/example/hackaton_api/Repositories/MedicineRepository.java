package com.example.hackaton_api.Repositories;

import com.example.hackaton_api.Models.Medicine;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
  @Query("select m from Medicine m where m.quantity <= :threshold")
  List<Medicine> findAllByQuantityAndQuantityIsLessThanOrEqualToThreshold(
          @Param("threshold") int threshold, Pageable pageable);
}

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

  @Query("select m from Medicine m where m.compartment.compartmentName = :compartmentName")
  List<Medicine> findAllByCompartmentName(
          @Param("compartmentName") String compartmentName, Pageable pageable);

  @Query(value = "SELECT * FROM medicine_treatment_list " +
          "INNER JOIN medicine ON medicine_treatment_list.medicine_id_medicine = medicine.id_medicine " +
          "INNER JOIN treatment ON medicine_treatment_list.treatment_list_id_treatment = treatment.id_treatment " +
          "WHERE treatment_name like ?1",
          countQuery = "SELECT count(*) FROM medicine",
          nativeQuery = true)
  List<Medicine> findByTreatmentName(String treatmentName, Pageable pageable);

  @Query(value = "SELECT * FROM medicine " +
          "INNER JOIN admission ON admission.id_admission = medicine.admission_id_admission " +
          "WHERE admission_name like ?1",
          countQuery = "SELECT count(*) FROM medicine",
          nativeQuery = true)
  List<Medicine> findByAdmissionName(String admissionName, Pageable pageable);

  @Query(value = "SELECT * FROM medicine_treatment_list " +
          "INNER JOIN medicine ON medicine_treatment_list.medicine_id_medicine = medicine.id_medicine " +
          "INNER JOIN treatment ON medicine_treatment_list.treatment_list_id_treatment = treatment.id_treatment " +
          "INNER JOIN admission ON admission.id_admission = medicine.admission_id_admission " +
          "WHERE treatment_name like ?2 AND admission_name = ?1",
          countQuery = "SELECT count(*) FROM medicine",
          nativeQuery = true)
  List<Medicine> findByAdmissionNameAndTreatmentName(String admissionName, String treatmentName, Pageable pageable);
}

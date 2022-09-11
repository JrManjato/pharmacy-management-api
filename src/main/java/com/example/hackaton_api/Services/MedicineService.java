package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.Admission;
import com.example.hackaton_api.Models.Compartment;
import com.example.hackaton_api.Models.CreateMedicine;
import com.example.hackaton_api.Models.History;
import com.example.hackaton_api.Models.Medicine;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Models.UpdateMedicine;
import com.example.hackaton_api.Repositories.MedicineRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
@Setter
public class MedicineService {

  private MedicineRepository medicineRepository;

  private AdmissionService admissionService;

  private CompartmentService compartmentService;

  private TreatmentService treatmentService;

  private HistoryService historyService;

  public List<Medicine> getMedicines(int page, int pageSize) {
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    return medicineRepository.findAll(pageable)
            .stream().toList();
  }

  public List<Medicine> getMedicinesByQuantityAndThreshold(int threshold, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    return medicineRepository.findAllByQuantityAndQuantityIsLessThanOrEqualToThreshold(threshold, pageable)
            .stream().toList();
  }

  public List<Medicine> getMedicinesByCompartmentName(String compartmentName, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    return medicineRepository.findAllByCompartmentName(compartmentName, pageable)
            .stream().toList();
  }

  public List<Medicine> getMedicinesByTreatmentName(String treatmentName, String admissionName, int page, int pageSize) {
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    if (admissionName == null && treatmentName != null) {
      return medicineRepository.findByTreatmentName(treatmentName, pageable)
              .stream().toList();
    } else if (admissionName != null && treatmentName == null) {
      return medicineRepository.findByAdmissionName(admissionName, pageable)
              .stream().toList();
    }else if (admissionName != null && treatmentName != null) {
      return medicineRepository.findByAdmissionNameAndTreatmentName(admissionName, treatmentName, pageable)
              .stream().toList();
    }
    return medicineRepository.findAll(pageable)
            .stream().toList();
  }

  public Medicine addMedicine(CreateMedicine createMedicine) {
    Medicine newMedicine = new Medicine();

    Admission admission = admissionService.getAdmissionByName(createMedicine.getAdmissionName());
    Compartment compartment = compartmentService.getCompartmentByName(createMedicine.getCompartmentName());
    List<Treatment> treatmentList = new ArrayList<>();

    for (String treatmentName : createMedicine.getTreatmentName()) {
      Treatment treatment = treatmentService.getTreatmentByName(treatmentName);
      treatmentList.add(treatment);
    }

    newMedicine.setMedicineName(createMedicine.getMedicineName());
    newMedicine.setAdmission(admission);
    newMedicine.setCompartment(compartment);
    newMedicine.setTreatmentList(treatmentList);

    return medicineRepository.save(newMedicine);
  }

  public Medicine modifyMedicine(UpdateMedicine currentMedicine) {

    Admission admission = admissionService.getAdmissionByName(currentMedicine.getAdmissionName());
    Compartment compartment = compartmentService.getCompartmentByName(currentMedicine.getCompartmentName());
    List<Treatment> treatmentList = new ArrayList<>();

    for (String treatmentName : currentMedicine.getTreatmentName()) {
      Treatment treatment = treatmentService.getTreatmentByName(treatmentName);
      treatmentList.add(treatment);
    }

    if (medicineRepository.existsById(currentMedicine.getIdMedicine())) {
      Medicine newMedicine = medicineRepository.findById(currentMedicine.getIdMedicine()).get();

      newMedicine.setMedicineName(currentMedicine.getMedicineName());
      newMedicine.setTreatmentList(treatmentList);
      newMedicine.setAdmission(admission);
      newMedicine.setCompartment(compartment);

      return medicineRepository.save(newMedicine);
    } else {
      return null;
    }
  }

  public Medicine replenishMedicine(int idMedicine, History history) {
    if (medicineRepository.existsById(idMedicine) && history.getQuantity() > 0) {
      Medicine currentMedicine = medicineRepository.findById(idMedicine).get();

      Instant instant = Instant.now();
      history.setOperationDateTime(instant);
      history.setMedicine(currentMedicine);
      historyService.addHistory(history);

      currentMedicine.setQuantity(currentMedicine.getQuantity() + history.getQuantity());
      return medicineRepository.save(currentMedicine);
    } else {
      return null;
    }
  }

  public Medicine consumeMedicine(int idMedicine, History history) {
    Medicine currentMedicine = medicineRepository.findById(idMedicine).get();
    if (medicineRepository.existsById(idMedicine) && currentMedicine.getQuantity() >= history.getQuantity()) {

      Instant instant = Instant.now();
      history.setOperationDateTime(instant);
      history.setMedicine(currentMedicine);
      historyService.addHistory(history);

      currentMedicine.setQuantity(currentMedicine.getQuantity() - history.getQuantity());
      return medicineRepository.save(currentMedicine);

    } else {
      return null;
    }
  }
}
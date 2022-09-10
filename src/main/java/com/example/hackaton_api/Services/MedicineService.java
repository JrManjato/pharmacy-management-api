package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.*;
import com.example.hackaton_api.Repositories.BookRepository;
import com.example.hackaton_api.Repositories.MedicineRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Comparator.comparing;

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
    List<Medicine> list = medicineRepository.findAll(pageable)
            .stream().toList();
    return list;
  }

  public Medicine addMedicine(Medicine medicine, List<String> treatmentNameList, String admissionName, String compartmentName) {
    Admission admission = admissionService.getAdmissionByName(admissionName);
    Compartment compartment = compartmentService.getCompartmentByName(compartmentName);
    List<Treatment> treatmentList = new ArrayList<>();
    for(String treatmentName: treatmentNameList){
      Treatment treatment = treatmentService.getTreatmentByName(treatmentName);
      treatmentList.add(treatment);
    }

    medicine.setAdmission(admission);
    medicine.setCompartment(compartment);
    medicine.setTreatmentList(treatmentList);

    return medicineRepository.save(medicine);
  }

  public Medicine modifyMedicine(Medicine medicine) {
    if(medicineRepository.existsById(medicine.getIdMedicine())){
      Medicine newMedicine = medicineRepository.findById(medicine.getIdMedicine()).get();
      newMedicine.setMedicineName(medicine.getMedicineName());
      newMedicine.setTreatmentList(medicine.getTreatmentList());
      newMedicine.setAdmission(medicine.getAdmission());
      newMedicine.setCompartment(medicine.getCompartment());
      return medicineRepository.save(newMedicine);
    }
    else{
      return null;
    }
  }

  public Medicine replenishMedicine(int idMedicine, History history) {
    if(medicineRepository.existsById(idMedicine) && history.getQuantity() > 0){
      Medicine currentMedicine = medicineRepository.findById(idMedicine).get();

      Instant instant = Instant.now();
      history.setOperationDateTime(instant);
      history.setMedicine(currentMedicine);
      historyService.addHistory(history);

      currentMedicine.setQuantity(currentMedicine.getQuantity() + history.getQuantity());
      return medicineRepository.save(currentMedicine);
    }
    else{
      return null;
    }
  }

  public Medicine consumeMedicine(int idMedicine, History history) {
    Medicine currentMedicine = medicineRepository.findById(idMedicine).get();
    if(medicineRepository.existsById(idMedicine) && currentMedicine.getQuantity() > history.getQuantity()){

        Instant instant = Instant.now();
        history.setOperationDateTime(instant);
        history.setMedicine(currentMedicine);
        historyService.addHistory(history);

        currentMedicine.setQuantity(currentMedicine.getQuantity() - history.getQuantity());
        return medicineRepository.save(currentMedicine);

    }
    else{
      return null;
    }
  }
}

// RequestBody for replenishment or consumption
//{
//        "description": "Livraison venant du port de Toamasina",
//        "operation": "réapprovisionnement",
//        "quantity": 2000
//}
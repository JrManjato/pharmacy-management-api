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


      return medicineRepository.save(newMedicine);
    }
    else{
      return null;
    }
  }
}

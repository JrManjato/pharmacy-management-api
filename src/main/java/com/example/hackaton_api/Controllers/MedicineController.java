package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Models.Medicine;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Services.BookService;
import com.example.hackaton_api.Services.MedicineService;
import com.example.hackaton_api.Services.TreatmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Getter
@Setter
@CrossOrigin
public class MedicineController {

  private MedicineService service;

  @GetMapping("/medicines")
  public List<Medicine> getAllMedicines(@RequestParam(name = "pageNumber") int page,
                                         @RequestParam(name = "pageSize") int pageSize) {
    return service.getMedicines(page, pageSize);
  }

  @PostMapping("/medicine/{treatmentNameList}/{admissionName}/{compartmentName}")
  public Medicine addMedicine(@PathVariable List<String> treatmentNameList, @PathVariable String admissionName, @PathVariable String compartmentName, @RequestBody Medicine medicine) {

    return service.addMedicine(medicine, treatmentNameList, admissionName, compartmentName);
  }

}
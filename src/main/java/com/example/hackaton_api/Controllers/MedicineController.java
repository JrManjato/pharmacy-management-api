package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.CreateMedicine;
import com.example.hackaton_api.Models.History;
import com.example.hackaton_api.Models.Medicine;
import com.example.hackaton_api.Models.UpdateMedicine;
import com.example.hackaton_api.Services.MedicineService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

  @GetMapping("/medicines/{threshold}")
  public List<Medicine> getMedicinesByGivenThreshold(@RequestParam(name = "pageNumber") int page,
                                                     @RequestParam(name = "pageSize") int pageSize,
                                                     @PathVariable int threshold) {
    return service.getMedicinesByQuantityAndThreshold(threshold, page, pageSize);
  }

  @PostMapping("/create-medicine")
  public Medicine addMedicine(@RequestBody CreateMedicine createMedicine) {
    return service.addMedicine(createMedicine);
  }

  @PutMapping("/modify-medicine")
  public Medicine modifyMedicine(@RequestBody UpdateMedicine currentMedicine) {
    return service.modifyMedicine(currentMedicine);
  }

  @PutMapping("/medicine/replenishement/{idMedicine}")
  public Medicine replenishMedicine(@PathVariable int idMedicine, @RequestBody History history) {
    return service.replenishMedicine(idMedicine, history);
  }

  @PutMapping("/medicine/consumption/{idMedicine}")
  public Medicine consumeMedicine(@PathVariable int idMedicine, @RequestBody History history) {
    return service.consumeMedicine(idMedicine, history);
  }

}
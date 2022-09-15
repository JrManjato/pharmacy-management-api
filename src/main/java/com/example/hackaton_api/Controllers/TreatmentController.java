package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Medicine;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Services.TreatmentService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Getter
@Setter
@CrossOrigin
public class TreatmentController {

  private TreatmentService service;

  @GetMapping("/treatments")
  public List<Treatment> getAllTreatmentsPerPage(@RequestParam(name = "pageNumber") int page,
                                          @RequestParam(name = "pageSize") int pageSize) {
    return service.getTreatmentsPerPage(page, pageSize);
  }

  @GetMapping("/treatment-list")
  public List<Treatment> getAllTreatments() {
    return service.getTreatments();
  }

  @PostMapping("/treatment")
  public Treatment addTreatment(@RequestBody Treatment treatment) {
    return service.addTreatment(treatment);
  }

  // The post below is made to insert the starting data.
  @PostMapping("/create-treatments")
  public List<Treatment> addTreatments(@RequestBody List<Treatment> TreatmentsList) {
    return service.addTreatments(TreatmentsList);
  }

}
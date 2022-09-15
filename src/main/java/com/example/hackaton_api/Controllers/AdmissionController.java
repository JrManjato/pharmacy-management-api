package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Admission;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Services.AdmissionService;
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
public class AdmissionController {

  private AdmissionService service;

  @GetMapping("/admissions")
  public List<Admission> getAllAdmissions() {
    return service.getAdmissions();
  }

}
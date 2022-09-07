package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.Admission;
import com.example.hackaton_api.Repositories.AdmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdmissionService {
  private AdmissionRepository admissionRepository;

  public Admission getAdmissionByName(String name) {
    return admissionRepository.findByAdmissionName(name);
  }
}

package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.Admission;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Repositories.AdmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AdmissionService {
  private AdmissionRepository admissionRepository;

  public List<Admission> getAdmissions(){
    List<Admission> list = admissionRepository.findAll()
            .stream().toList();
    return list;
  }
  public Admission getAdmissionByName(String name) {
    return admissionRepository.findByAdmissionName(name);
  }
}

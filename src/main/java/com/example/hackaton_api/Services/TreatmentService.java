package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Models.Category;
import com.example.hackaton_api.Models.Compartment;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Repositories.CompartmentRepository;
import com.example.hackaton_api.Repositories.TreatmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Getter
@Setter
public class TreatmentService {
  private TreatmentRepository treatmentRepository;

  public List<Treatment> getTreatments(int page, int pageSize){
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    List<Treatment> list = treatmentRepository.findAll(pageable)
            .stream().toList();
    return list;
  }

  public Treatment addTreatment(Treatment treatment) {
    return treatmentRepository.save(treatment);
  }

  public Treatment getTreatmentByName(String name) {
    return treatmentRepository.findByTreatmentName(name);
  }
}

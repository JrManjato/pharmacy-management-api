package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.Compartment;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Repositories.CompartmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
@Getter
@Setter
public class CompartmentService {
  private CompartmentRepository compartmentRepository;

  public List<Compartment> getCompartment(int page, int pageSize){
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    return compartmentRepository.findAll(pageable)
            .stream().toList();
  }
  
  public Compartment addCompartment(Compartment compartment) {
    return compartmentRepository.save(compartment);
  }

  public void delete(int id){
    compartmentRepository.deleteById(id);
  }

  public void multipleDelete(List<Integer> ids){
    for (int i = 0; i < ids.size(); i++) {
      compartmentRepository.deleteById(ids.get(i));
    }
  }

  public Compartment getCompartmentByName(String name) {
    return compartmentRepository.findByCompartmentName(name);
  }

  public List<Compartment> addCompartments(List<Compartment> CompartmentsList) {
    return compartmentRepository.saveAll(CompartmentsList);
  }
}

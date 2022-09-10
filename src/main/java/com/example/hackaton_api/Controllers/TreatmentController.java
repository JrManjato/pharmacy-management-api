package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Services.BookService;
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
public class TreatmentController {

  private TreatmentService service;

  @GetMapping("/treatments")
  public List<Treatment> getAllTreatments(@RequestParam(name = "pageNumber") int page,
                                          @RequestParam(name = "pageSize") int pageSize) {
    return service.getTreatments(page, pageSize);
  }

  @PostMapping("/treatment")
  public Treatment addTreatment(@RequestBody Treatment treatment) {
    return service.addTreatment(treatment);
  }

}
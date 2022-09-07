package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Models.Compartment;
import com.example.hackaton_api.Services.BookService;
import com.example.hackaton_api.Services.CompartmentService;
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
public class CompartmentController {

  private CompartmentService service;

  @GetMapping("/compartments")
  public List<Compartment> getAllCompartments(@RequestParam(name = "pageNumber") int page,
                                              @RequestParam(name = "pageSize") int pageSize){
    return service.getCompartment(page,pageSize);
  }

  @PostMapping("/compartment")
  public Compartment addCompartment(@RequestBody Compartment compartment){
    return service.addCompartment(compartment);
  }

  @DeleteMapping("/compartment/{id}")
  public void deleteCompartment(@PathVariable int id){
    service.delete(id);
  }

  @DeleteMapping("/compartments/{ids}")
  public void deleteCompartments(@PathVariable List<Integer> ids){
     service.multipleDelete(ids);
  }

}

//http://localhost:8080/compartment?pageNumber=1&pageSize=10
package com.example.hackaton_api.Controllers;

import com.example.hackaton_api.Models.Book;
import com.example.hackaton_api.Models.History;
import com.example.hackaton_api.Models.Treatment;
import com.example.hackaton_api.Services.BookService;
import com.example.hackaton_api.Services.HistoryService;
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
public class HistoryController {

  private HistoryService service;

  @GetMapping("/histories")
  public List<History> getAllHistories(@RequestParam(name = "pageNumber") int page,
                                        @RequestParam(name = "pageSize") int pageSize) {
    return service.getHistories(page, pageSize);
  }

  @PostMapping("/history")
  public History addHistory(@RequestBody History history) {
    return service.addHistory(history);
  }
  @DeleteMapping("/history/{id}")
  public void deleteHistory(@PathVariable int id){
     service.delete(id);
  }

  @DeleteMapping("/histories/{ids}")
  public void deleteHistories(@PathVariable List<Integer> ids){
     service.multipleDelete(ids);
  }


}
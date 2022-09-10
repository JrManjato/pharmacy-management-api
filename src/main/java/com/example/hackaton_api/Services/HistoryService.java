package com.example.hackaton_api.Services;

import com.example.hackaton_api.Models.*;
import com.example.hackaton_api.Repositories.CompartmentRepository;
import com.example.hackaton_api.Repositories.HistoryRepository;
import com.example.hackaton_api.Repositories.TreatmentRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;


@Service
@AllArgsConstructor
@Getter
@Setter
public class HistoryService {
  private HistoryRepository historyRepository;

  public List<History> getHistories(int page, int pageSize){
    Pageable pageable = PageRequest.of(page - 1, pageSize);
    return historyRepository.findAll(pageable)
            .stream().toList();
  }

  public History addHistory(History history) {
    Instant instant = Instant.now();
    history.setOperationDateTime(instant);
    return historyRepository.save(history);
  }
  public void delete(int id){
    historyRepository.deleteById(id);
  }

  public void multipleDelete(List<Integer> ids){
    for (Integer id : ids) {
      historyRepository.deleteById(id);
    }
  }
}

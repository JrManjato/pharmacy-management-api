package com.example.hackaton_api.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CreateMedicine implements Serializable {
  private String medicineName;
  private List <String> treatmentName;
  private String admissionName;
  private String compartmentName;
}

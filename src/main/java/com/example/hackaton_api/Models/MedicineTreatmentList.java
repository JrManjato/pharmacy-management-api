package com.example.hackaton_api.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class MedicineTreatmentList implements Serializable {
  private int medicineIdMedicine;
  private int treatmentListIdTreatment;
}

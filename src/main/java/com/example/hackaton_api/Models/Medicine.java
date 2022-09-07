package com.example.hackaton_api.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class Medicine implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idMedicine")
  private int idMedicine;

  @Column(name = "medicineName")
  private String medicineName;

  @ManyToMany
  private List<Treatment> treatmentList;

  @ManyToOne
  private Admission admission;

  @ManyToOne
  private Compartment compartment;

  @Column(name = "quantity")
  private int quantity;
}

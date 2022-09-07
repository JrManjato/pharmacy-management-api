package com.example.hackaton_api.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class History implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "idHistory")
  private int idHistory;

  @Column(name = "description")
  private String description;

  @Column(name = "operation")
  private String operation;

  @Column(name = "operationDateTime")
  private Date operationDateTime;

  @Column(name = "quantity")
  private Date quantity;

  @ManyToOne
  private Medicine medicine;
}

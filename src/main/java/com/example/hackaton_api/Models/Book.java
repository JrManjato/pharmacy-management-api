package com.example.hackaton_api.Models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@ToString
public class Book implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBook")
    private int idBook;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name="pageNumber")
    private int pageNumber;

    @ManyToOne
    private Category category;

    private Status status;

    private int loansNumber;

    public enum Status{
        available, unavailable
    }

}

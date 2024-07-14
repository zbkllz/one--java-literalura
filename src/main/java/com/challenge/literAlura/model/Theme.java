package com.challenge.literAlura.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "temas")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Getter
    private String name;
    @Setter
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    public Theme() {}

    public Theme(String name) {
        this.name = name;
    }

}

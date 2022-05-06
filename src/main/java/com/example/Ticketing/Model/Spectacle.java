package com.example.ticketing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="spectacle")
public class Spectacle{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "title", unique = true, nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(name = "length", nullable = false)
    private Integer length;

    @Column(name = "min_age")
    private Integer minAge;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "spectacle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> spectacleReservations;
}

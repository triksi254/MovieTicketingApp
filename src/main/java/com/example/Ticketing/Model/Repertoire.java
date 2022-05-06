package com.example.ticketing.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="repertoire")
@NoArgsConstructor
public class Repertoire {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd''HH:mm")
    @Column(name= "date")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "spectacle_id", referencedColumnName = "id")
    private Spectacle spectacle;

    @OneToMany(mappedBy = "repertoire", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<Reservation> reservations;
}

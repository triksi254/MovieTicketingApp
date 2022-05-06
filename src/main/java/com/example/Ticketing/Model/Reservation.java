package com.example.ticketing.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "reservation")
public class Reservation {

        @GeneratedValue(strategy = GenerationType.AUTO)
        @Id
        @Column(name = "id")
        private Long id;

        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "movie_id", referencedColumnName = "id")
        private Movie movie;

        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "spectacle_id")
        private Spectacle spectacle;

        @ManyToOne(cascade = CascadeType.MERGE)
        @JoinColumn(name = "user_id")
        private Users users;

        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "ticket_id",referencedColumnName="id")
        private Ticket ticket;

        @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
        @JoinColumn(name = "repertoire_id")
        private Repertoire repertoire;
}

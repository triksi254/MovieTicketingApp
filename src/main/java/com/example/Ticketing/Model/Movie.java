package com.example.ticketing.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.time.Duration;
import java.util.List;


@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String type;
    private String language;
    private String cast;
    private Long duration;
    private String plot;
    @Column(name = "min_age")
    private Integer minAge;
    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "movie", orphanRemoval = true)
    private List<Repertoire> repertoires;



    public Movie(String imageUrl, List<Reservation> reservations) {
        this.imageUrl = imageUrl;
        this.reservations = reservations;
    }

    public Movie(Integer minAge, String imageUrl, List<Reservation> reservations) {
        this.minAge = minAge;
        this.imageUrl = imageUrl;
        this.reservations = reservations;
    }

    public Movie(@JsonProperty("id") Long id,
                 @JsonProperty("name") String name,
                 @JsonProperty("type") String type,
                 @JsonProperty("language") String language,
                 @JsonProperty("cast") String cast,
                 @JsonProperty("duration") Long duration,
                 @JsonProperty("plot") String plot, Integer minAge, String imageUrl, URL imageUrl1, List<Reservation> reservations) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.language = language;
        this.cast = cast;
        this.duration = duration;
        this.plot = plot;
        this.minAge = minAge;
        this.imageUrl = imageUrl;
        this.reservations = reservations;
    }

    public Movie(String name, String type, String language, String cast, Long duration, String plot, Integer minAge, String imageUrl, URL imageUrl1, List<Reservation> reservations) {
        this.name = name;
        this.type = type;
        this.language = language;
        this.cast = cast;
        this.duration = duration;
        this.plot = plot;
        this.minAge = minAge;
        this.imageUrl = imageUrl;
        this.reservations = reservations;
    }

    public Movie() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }


    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", Language='" + language + '\'' +
                ", cast='" + cast + '\'' +
                ", Duration=" + duration +
                ", plot='" + plot + '\'' +
                '}';
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Repertoire> getRepertoires() {
        return repertoires;
    }

    public void setRepertoires(List<Repertoire> repertoires) {
        this.repertoires = repertoires;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getDuration() {
        return duration;
    }
}

package com.example.Ticketing.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name="movie")
public class Movie {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String type;
    private String language;
    private String cast;
    private java.time.Duration duration;
    private String plot;


    public Movie() {
    }

    public Movie (@JsonProperty("id")Long id,
                  @JsonProperty("name")String name,
                  @JsonProperty("type")String type,
                  @JsonProperty("language")String language,
                  @JsonProperty("cast")String cast,
                  @JsonProperty("duration")java.time.Duration duration,
                  @JsonProperty("plot")String plot) {
        this.Id = id;
        this.name = name;
        this.type = type;
        this.language = language;
        this.cast = cast;
        this.duration = duration;
        this.plot = plot;
    }

    public Movie(String name, String type, String language, String cast, java.time.Duration duration, String plot) {
        this.name = name;
        this.type = type;
        this.language = language;
        this.cast = cast;
        this.duration = duration;
        this.plot = plot;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public java.time.Duration getDuration() {
        return duration;
    }

    public void setDuration(java.time.Duration duration) {
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
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", Language='" + language + '\'' +
                ", cast='" + cast + '\'' +
                ", Duration=" + duration +
                ", plot='" + plot + '\'' +
                '}';
    }
}

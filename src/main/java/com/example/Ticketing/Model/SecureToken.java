package com.example.ticketing.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import javax.persistence.*;
import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name="secureToken")
public class SecureToken {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String token;

    @Column
    @Transient
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(updatable=false)
    @Basic(optional=false)
    private LocalDateTime expireAt;
    /** many token have a single customer **/
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName ="id")
    private Users user;

    @Transient
    private boolean isExpired;
    public boolean isExpired(){
        return getExpireAt().isBefore(LocalDateTime.now());
    }

    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "SecureToken{" +
                "id=" + id +
                ", token='" + token + '\'' +
                ", timestamp=" + timestamp +
                ", expireAt=" + expireAt +
                ", user=" + user +
                ", isExpired=" + isExpired +
                '}';
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setExpired(boolean expired) {
        isExpired = expired;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Users getUser() {
        return user;
    }
}

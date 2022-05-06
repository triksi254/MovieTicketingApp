package com.example.ticketing.model;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name="users")
//includes the users entity
public class Users {
    @GeneratedValue( strategy=GenerationType.AUTO)
    @Id
    private long id;
 //  private String username;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;

    @Column(unique=true)
    private String email;

    private String password;
    private boolean disabled;
    private boolean accountExpired;
    private boolean accountLocked;
    private boolean credentialsExpired;
    private boolean accountVerified;
    /** The collection of tokens. */
    @OneToMany(mappedBy= ("user"))
    private Set<SecureToken> tokens;


    public Users() {

    }

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "user_role",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    List<Role> roles;

/*    public Users() {
    }*/

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public boolean isAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public boolean isAccountLocked() {
        return accountLocked;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public void setAccountVerified(boolean accountVerified) {
        this.accountVerified=accountVerified;
    }
    public boolean isAccountVerified() {
        return accountVerified;
    }

    public String getEmail() {
        return email;
    }

    public String findByEmail() {
        return email;
    }

    public Set getTokens() {
        return tokens;
    }

    public void setTokens(Set tokens) {
        this.tokens = tokens;
    }

    public boolean isPresent() {
    return false;}

//
//    public String findByEmail() {
//        return email;
//    }
}

//    public List<Role> getRoles() {
//        return roles;
//    }
//
//    public void setRoles(List<Role> roles) {
//        this.roles = roles;
//    }


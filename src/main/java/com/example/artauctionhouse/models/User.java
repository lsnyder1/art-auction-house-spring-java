package com.example.artauctionhouse.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lonny on 11/11/2017.
 */
@Entity
public class User {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @GeneratedValue
    @Id
    private int id;

    private List<Art> ownedArt;

    public User(String username, String password){
        this.password=password;
        this.username=username;
    }
    public User(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }


}

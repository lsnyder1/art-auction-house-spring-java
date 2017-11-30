package com.example.artauctionhouse.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by lonny on 11/30/2017.
 */
@Entity
public class Art {

    @NotNull
    private String title;

    @ManyToOne
    private User owner;

    @NotNull
    private String imageLocation;

    @NotNull
    @GeneratedValue
    @Id
    private int id;

    private String style;

    public Art(String title, String imageLocation, String style) {
        this.title = title;
        this.imageLocation = imageLocation;
        this.style = style;
    }

    public Art() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getId() {
        return id;
    }
}

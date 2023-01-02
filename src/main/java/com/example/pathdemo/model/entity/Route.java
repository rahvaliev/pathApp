package com.example.pathdemo.model.entity;

import com.example.pathdemo.model.entity.enums.LevelEnum;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "routes")
public class Route extends BaseEntity{
    private String gpxCoordinate;
    private String description;
    private LevelEnum level;
    private String name;
    private User author;
    private Set<Picture> pictures;
    private String videoUrl;



    private Set<Category> categories;
    @Column(columnDefinition = "LONGTEXT")
    public String getGpxCoordinate() {
        return gpxCoordinate;
    }

    public void setGpxCoordinate(String gpxCoordinate) {
        this.gpxCoordinate = gpxCoordinate;
    }
    @Enumerated(EnumType.STRING)
    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }
    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @ManyToMany
    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
    @OneToMany(mappedBy = "route",fetch = FetchType.EAGER)
    public Set<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Set<Picture> pictures) {
        this.pictures = pictures;
    }
    /*
        • id - Accepts UUID String or Long values 
    • gpx coordinates - Accepts very long text values
    • level - Accepts the levels of the routes (BEGINNER, INTERMEDIATE, ADVANCED) as values
    • name - Accepts String values
    • author - Accepts User Entities as values
    • video url – Accepts the ids of youtube videos as values
     */
}

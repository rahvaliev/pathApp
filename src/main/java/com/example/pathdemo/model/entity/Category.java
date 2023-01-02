package com.example.pathdemo.model.entity;

import com.example.pathdemo.model.entity.enums.CategoryNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{
    private CategoryNameEnum name;
    private String description;
    @Enumerated(EnumType.STRING)
    public CategoryNameEnum getName() {
        return name;
    }

    public void setName(CategoryNameEnum name) {
        this.name = name;
    }
    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
        • id - Accepts UUID String or Long values 
    • name - Accepts String values (PEDESTRIAN, BICYCLE, MOTORCYCLE, CAR)
    • description - Accepts very long String values
     */
}

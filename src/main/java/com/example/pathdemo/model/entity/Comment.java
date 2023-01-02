package com.example.pathdemo.model.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{
    private boolean approved;
    private LocalDateTime created;
    private String textContent;
    private User author;
    private Route route;

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }
    @Column(nullable = false)
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    @Column(columnDefinition = "TEXT")
    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }
    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
    @ManyToOne
    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
/*
        • id - Accepts UUID String or Long values
    • approved - Accepts boolean values
    • created - Accepts Date and Time values
        ◦  The values should not be future dates
    • text content - Accepts very long text values
    • author - Accepts User Entities as values
    • route - Accepts Route Entities as values
     */
}

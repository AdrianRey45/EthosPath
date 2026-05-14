package com.example.EthosPath.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "missions")
@NoArgsConstructor
@AllArgsConstructor
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Integer xpReward;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Difficulty difficulty;

    private String type;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    public enum Difficulty {
        EASY, MEDIUM, HARD
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getXpReward() { return xpReward; }
    public void setXpReward(Integer xpReward) { this.xpReward = xpReward; }

    public Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Difficulty difficulty) { this.difficulty = difficulty; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public User getCreator() { return creator; }
    public void setCreator(User creator) { this.creator = creator; }
}
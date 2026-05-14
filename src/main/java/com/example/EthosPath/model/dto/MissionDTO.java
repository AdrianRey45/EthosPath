package com.example.EthosPath.model.dto;

import com.example.EthosPath.model.entity.Mission;

public class MissionDTO {
    private Long id;
    private String title;
    private String description;
    private Integer xpReward;
    private Mission.Difficulty difficulty;
    private String type;
    private String categoryName;
    private String creatorName;

    // Getters y Setters manuales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Integer getXpReward() { return xpReward; }
    public void setXpReward(Integer xpReward) { this.xpReward = xpReward; }

    public Mission.Difficulty getDifficulty() { return difficulty; }
    public void setDifficulty(Mission.Difficulty difficulty) { this.difficulty = difficulty; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getCreatorName() { return creatorName; }
    public void setCreatorName(String creatorName) { this.creatorName = creatorName; }
}
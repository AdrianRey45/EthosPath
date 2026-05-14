package com.example.EthosPath.model.dto;

public class UserResponse {
    private String id;
    private String username;
    private String email;
    private Integer level;
    private Integer currentXp;
    private Integer totalXp;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Integer getCurrentXp() { return currentXp; }
    public void setCurrentXp(Integer currentXp) { this.currentXp = currentXp; }

    public Integer getTotalXp() { return totalXp; }
    public void setTotalXp(Integer totalXp) { this.totalXp = totalXp; }
}
package com.example.EthosPath.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "users")
@NoArgsConstructor 
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private Integer currentXp = 0;
    private Integer totalXp = 0;
    private Integer level = 1;

    @ManyToMany
    @JoinTable(
        name = "user_friends",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private Set<User> friends = new HashSet<>();

    @OneToMany(mappedBy = "creator")
    private List<Mission> createdMissions = new ArrayList<>();

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Integer getCurrentXp() { return currentXp; }
    public void setCurrentXp(Integer currentXp) { this.currentXp = currentXp; }

    public Integer getTotalXp() { return totalXp; }
    public void setTotalXp(Integer totalXp) { this.totalXp = totalXp; }

    public Integer getLevel() { return level; }
    public void setLevel(Integer level) { this.level = level; }

    public Set<User> getFriends() { return friends; }
    public void setFriends(Set<User> friends) { this.friends = friends; }

    public List<Mission> getCreatedMissions() { return createdMissions; }
    public void setCreatedMissions(List<Mission> createdMissions) { this.createdMissions = createdMissions; }
}
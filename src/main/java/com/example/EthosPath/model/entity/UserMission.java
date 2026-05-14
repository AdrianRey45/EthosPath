package com.example.EthosPath.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_missions")
@NoArgsConstructor
@AllArgsConstructor
public class UserMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "mission_id", nullable = false)
    private Mission mission;

    @Enumerated(EnumType.STRING)
    private MissionStatus status = MissionStatus.STARTED;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime startedAt;

    private LocalDateTime completedAt;

    public enum MissionStatus {
        STARTED, COMPLETED, FAILED
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Mission getMission() { return mission; }
    public void setMission(Mission mission) { this.mission = mission; }

    public MissionStatus getStatus() { return status; }
    public void setStatus(MissionStatus status) { this.status = status; }

    public LocalDateTime getStartedAt() { return startedAt; }
    public void setStartedAt(LocalDateTime startedAt) { this.startedAt = startedAt; }

    public LocalDateTime getCompletedAt() { return completedAt; }
    public void setCompletedAt(LocalDateTime completedAt) { this.completedAt = completedAt; }

    public static UserMissionBuilder builder() {
        return new UserMissionBuilder();
    }

    public static class UserMissionBuilder {
        private User user;
        private Mission mission;
        private MissionStatus status = MissionStatus.STARTED;

        public UserMissionBuilder user(User user) {
            this.user = user;
            return this;
        }

        public UserMissionBuilder mission(Mission mission) {
            this.mission = mission;
            return this;
        }

        public UserMissionBuilder status(MissionStatus status) {
            this.status = status;
            return this;
        }

        public UserMission build() {
            UserMission um = new UserMission();
            um.setUser(this.user);
            um.setMission(this.mission);
            um.setStatus(this.status);
            return um;
        }
    }
}
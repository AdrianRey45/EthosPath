package com.example.EthosPath.model.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rivalries")
public class Ryvalry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    @ManyToOne
    @JoinColumn(name = "challenger_id", nullable = false)
    private User challenger;

    @ManyToOne
    @JoinColumn(name = "opponent_id", nullable = false)
    private User opponent;

    private Integer challengerXpStart;
    private Integer opponentXpStart;

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private RivalryStatus status = RivalryStatus.PENDING;

    private String winnerId;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public User getChallenger() { return challenger; }
    public void setChallenger(User challenger) { this.challenger = challenger; }

    public User getOpponent() { return opponent; }
    public void setOpponent(User opponent) { this.opponent = opponent; }

    public Integer getChallengerXpStart() { return challengerXpStart; }
    public void setChallengerXpStart(Integer challengerXpStart) { this.challengerXpStart = challengerXpStart; }

    public Integer getOpponentXpStart() { return opponentXpStart; }
    public void setOpponentXpStart(Integer opponentXpStart) { this.opponentXpStart = opponentXpStart; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public RivalryStatus getStatus() { return status; }
    public void setStatus(RivalryStatus status) { this.status = status; }

    public String getWinnerId() { return winnerId; }
    public void setWinnerId(String winnerId) { this.winnerId = winnerId; }
}
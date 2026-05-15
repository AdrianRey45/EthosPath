package com.example.EthosPath.model.dto;

public class ChallengeRequest {
    private String challengerId;
    private String opponentId;

    public String getChallengerId() { return challengerId; }
    public void setChallengerId(String challengerId) { this.challengerId = challengerId; }

    public String getOpponentId() { return opponentId; }
    public void setOpponentId(String opponentId) { this.opponentId = opponentId; }
}
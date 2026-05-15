package com.example.EthosPath.controller;

import com.example.EthosPath.model.dto.ChallengeRequest;
import com.example.EthosPath.model.entity.RivalryStatus;
import com.example.EthosPath.model.entity.Ryvalry;
import com.example.EthosPath.services.RivalryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rivalries")
@CrossOrigin(origins = "*")
public class RivalryController {

    @Autowired
    private RivalryService rivalryService;

    @PostMapping("/challenge")
    public ResponseEntity<Ryvalry> createChallenge(@RequestBody ChallengeRequest request) {
        Ryvalry newRivalry = rivalryService.createChallenge(
            request.getChallengerId(), 
            request.getOpponentId()
        );
        return ResponseEntity.ok(newRivalry);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Ryvalry>> getUserRivalries(@PathVariable String userId) {
        return ResponseEntity.ok(rivalryService.getUserRivalries(userId));
    }

    @PutMapping("/{rivalryId}/status")
    public ResponseEntity<Void> updateStatus(
            @PathVariable String rivalryId, 
            @RequestParam RivalryStatus status) {
        rivalryService.updateRivalryStatus(rivalryId, status);
        return ResponseEntity.ok().build();
    }
}
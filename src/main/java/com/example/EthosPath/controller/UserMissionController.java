package com.example.EthosPath.controller;

import com.example.EthosPath.model.entity.UserMission;
import com.example.EthosPath.services.UserMissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-missions")
@CrossOrigin(origins = "*")
public class UserMissionController {

    @Autowired
    private UserMissionService userMissionService;

    @PostMapping("/start/{userId}/{missionId}")
    public ResponseEntity<UserMission> startMission(@PathVariable String userId, @PathVariable Long missionId) {
        UserMission userMission = userMissionService.startMission(userId, missionId);
        return ResponseEntity.ok(userMission);
    }

    @PostMapping("/complete/{userMissionId}")
    public ResponseEntity<UserMission> completeMission(@PathVariable Long userMissionId) {
        UserMission completed = userMissionService.completeMission(userMissionId);
        return ResponseEntity.ok(completed);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserMission>> getUserProgress(@PathVariable String userId) {
        List<UserMission> progress = userMissionService.getActiveMissions(userId);
        return ResponseEntity.ok(progress);
    }
}
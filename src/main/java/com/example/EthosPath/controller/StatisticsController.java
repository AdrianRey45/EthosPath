package com.example.EthosPath.controller;

import com.example.EthosPath.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/stats")
@CrossOrigin(origins = "*")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getUserStats(@PathVariable String userId) {
        Map<String, Object> stats = statisticsService.getUserSummary(userId);
        return ResponseEntity.ok(stats);
    }
}
package com.example.EthosPath.services;

import com.example.EthosPath.model.entity.User;
import com.example.EthosPath.model.entity.UserMission;
import com.example.EthosPath.repository.UserMissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {

    @Autowired
    private UserMissionRepository userMissionRepository;

    @Autowired
    private UserService userService;

    public Map<String, Object> getUserSummary(String userId) {
        User user = userService.getUserProfile(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        List<UserMission> missions = userMissionRepository.findByUserId(userId);
        
        long completed = missions.stream()
                .filter(m -> m.getStatus() == UserMission.MissionStatus.COMPLETED)
                .count();

        Map<String, Object> stats = new HashMap<>();
        stats.put("username", user.getUsername());
        stats.put("level", user.getLevel());
        stats.put("currentXp", user.getCurrentXp());
        stats.put("xpToNextLevel", user.getLevel() * 100);
        stats.put("missionsCompleted", completed);
        stats.put("totalFriends", user.getFriends().size());

        return stats;
    }
}
package com.example.EthosPath.services;

import com.example.EthosPath.model.entity.UserMission;
import com.example.EthosPath.model.entity.User;
import com.example.EthosPath.model.entity.Mission;
import com.example.EthosPath.repository.UserMissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserMissionService {

    @Autowired
    private UserMissionRepository userMissionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MissionService missionService;

    public UserMission startMission(String userId, Long missionId) {
        User user = userService.getUserProfile(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        Mission mission = missionService.getMissionById(missionId);

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMission.MissionStatus.STARTED)
                .build();

        return userMissionRepository.save(userMission);
    }

    public UserMission completeMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new RuntimeException("Progreso no encontrado"));

        if (userMission.getStatus() == UserMission.MissionStatus.COMPLETED) {
            throw new RuntimeException("Misión ya completada");
        }

        userMission.setStatus(UserMission.MissionStatus.COMPLETED);
        userMission.setCompletedAt(LocalDateTime.now());
        
        userService.addExperience(userMission.getUser().getId(), userMission.getMission().getXpReward());

        return userMissionRepository.save(userMission);
    }

    public List<UserMission> getActiveMissions(String userId) {
        return userMissionRepository.findByUserId(userId).stream()
                .filter(m -> m.getStatus() == UserMission.MissionStatus.STARTED)
                .toList();
    }
}
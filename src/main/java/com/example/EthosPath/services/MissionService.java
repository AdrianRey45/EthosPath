package com.example.EthosPath.services;

import com.example.EthosPath.model.entity.Mission;
import com.example.EthosPath.model.entity.UserMission;
import com.example.EthosPath.repository.MissionRepository;
import com.example.EthosPath.repository.UserMissionRepository;
import com.example.EthosPath.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    @Autowired
    private UserMissionRepository userMissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<Mission> getAllMissions() { return missionRepository.findAll(); }

    public List<Mission> getOfficialMissions() {
        return missionRepository.findAll().stream()
                .filter(m -> "OFFICIAL".equalsIgnoreCase(m.getType()))
                .collect(Collectors.toList());
    }
    
    public UserMission startMission(String userId, Long missionId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        var mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("Misión no encontrada"));

        UserMission userMission = UserMission.builder()
                .user(user)
                .mission(mission)
                .status(UserMission.MissionStatus.STARTED)
                .build();

        return userMissionRepository.save(userMission);
    }

    public void completeUserMission(Long userMissionId) {
        UserMission userMission = userMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new RuntimeException("Progreso de misión no encontrado"));

        if (userMission.getStatus() == UserMission.MissionStatus.COMPLETED) {
            throw new RuntimeException("Esta misión ya fue finalizada");
        }

        userMission.setStatus(UserMission.MissionStatus.COMPLETED);
        userMission.setCompletedAt(LocalDateTime.now());
        userMissionRepository.save(userMission);

        userService.addExperience(userMission.getUser().getId(), userMission.getMission().getXpReward());
    }

    public Mission createMission(Mission mission) { return missionRepository.save(mission); }
    
    public Mission getMissionById(Long id) {
        return missionRepository.findById(id).orElseThrow(() -> new RuntimeException("Misión no encontrada"));
    }

    public void deleteMission(Long id) { missionRepository.deleteById(id); }
}
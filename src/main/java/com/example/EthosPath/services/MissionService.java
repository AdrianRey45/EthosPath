package com.example.EthosPath.services;

import com.example.EthosPath.model.entity.Mission;
import com.example.EthosPath.repository.MissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public List<Mission> getOfficialMissions() {
        return missionRepository.findAll()
                .stream()
                .filter(m -> m.getType() != null && m.getType().equalsIgnoreCase("OFFICIAL"))
                .collect(Collectors.toList());
    }

    public List<Mission> getCommunityMissions() {
        return missionRepository.findAll()
                .stream()
                .filter(m -> m.getType() != null && m.getType().equalsIgnoreCase("COMMUNITY"))
                .collect(Collectors.toList());
    }

    public List<Mission> getMissionsByCategory(Long categoryId) {
        return missionRepository.findAll()
                .stream()
                .filter(m -> m.getCategory() != null && m.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public List<Mission> getMissionsByDifficulty(Mission.Difficulty difficulty) {
        return missionRepository.findAll()
                .stream()
                .filter(m -> m.getDifficulty() == difficulty)
                .collect(Collectors.toList());
    }

    public List<Mission> getMissionsByCreator(String userId) {
        return missionRepository.findAll()
                .stream()
                .filter(m -> m.getCreator() != null && m.getCreator().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public Mission createMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public Mission getMissionById(Long id) {
        return missionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Misión no encontrada"));
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }
}
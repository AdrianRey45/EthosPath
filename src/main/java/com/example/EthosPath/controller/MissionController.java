package com.example.EthosPath.controller;

import com.example.EthosPath.model.dto.MissionDTO;
import com.example.EthosPath.model.entity.Mission;
import com.example.EthosPath.services.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/missions")
@CrossOrigin(origins = "*")
public class MissionController {

    @Autowired
    private MissionService missionService;

    @GetMapping
    public ResponseEntity<List<MissionDTO>> getAllMissions() {
        List<Mission> missions = missionService.getAllMissions();
        List<MissionDTO> dtos = missions.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MissionDTO> getMissionById(@PathVariable Long id) {
        Mission mission = missionService.getMissionById(id);
        if (mission != null) {
            return ResponseEntity.ok(convertToDTO(mission));
        }
        return ResponseEntity.notFound().build();
    }

    private MissionDTO convertToDTO(Mission mission) {
        MissionDTO dto = new MissionDTO();
        dto.setId(mission.getId());
        dto.setTitle(mission.getTitle());
        dto.setDescription(mission.getDescription());
        dto.setXpReward(mission.getXpReward());
        dto.setDifficulty(mission.getDifficulty());
        dto.setType(mission.getType());
        
        if (mission.getCategory() != null) {
            dto.setCategoryName(mission.getCategory().getName());
        }
        if (mission.getCreator() != null) {
            dto.setCreatorName(mission.getCreator().getUsername());
        }
        
        return dto;
    }
}
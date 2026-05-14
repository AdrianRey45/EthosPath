package com.example.EthosPath.repository;

import com.example.EthosPath.model.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {
    List<Mission> findByTypeIgnoreCase(String type);
    List<Mission> findByCategoryId(Long categoryId);
    List<Mission> findByCreatorId(String creatorId);
    List<Mission> findByDifficulty(Mission.Difficulty difficulty);
}
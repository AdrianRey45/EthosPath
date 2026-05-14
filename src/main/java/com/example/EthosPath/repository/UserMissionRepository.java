package com.example.EthosPath.repository;

import com.example.EthosPath.model.entity.UserMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    List<UserMission> findByUserId(String userId);
}
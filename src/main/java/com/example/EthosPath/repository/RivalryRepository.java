package com.example.EthosPath.repository;

import com.example.EthosPath.model.entity.RivalryStatus;
import com.example.EthosPath.model.entity.Ryvalry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RivalryRepository extends JpaRepository<Ryvalry, String> {

    List<Ryvalry> findByChallengerIdOrOpponentId(String challengerId, String opponentId);

    List<Ryvalry> findByStatus(RivalryStatus status);

    @Query("SELECT r FROM Ryvalry r WHERE (r.challenger.id = :userId OR r.opponent.id = :userId) AND r.status = :status")
    List<Ryvalry> findActiveRivalriesByUser(@Param("userId") String userId, @Param("status") RivalryStatus status);
}
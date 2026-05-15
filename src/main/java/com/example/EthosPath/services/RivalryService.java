package com.example.EthosPath.services;

import com.example.EthosPath.model.entity.RivalryStatus;
import com.example.EthosPath.model.entity.Ryvalry;
import com.example.EthosPath.model.entity.User;
import com.example.EthosPath.repository.RivalryRepository;
import com.example.EthosPath.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RivalryService {

    @Autowired
    private RivalryRepository rivalryRepository;

    @Autowired
    private UserRepository userRepository;

    public Ryvalry createChallenge(String challengerId, String opponentId) {
        User challenger = userRepository.findById(challengerId)
                .orElseThrow(() -> new RuntimeException("Usuario retador no encontrado"));
        User opponent = userRepository.findById(opponentId)
                .orElseThrow(() -> new RuntimeException("Usuario oponente no encontrado"));

        Ryvalry rivalry = new Ryvalry();
        rivalry.setChallenger(challenger);
        rivalry.setOpponent(opponent);
        
        rivalry.setChallengerXpStart(challenger.getCurrentXp());
        rivalry.setOpponentXpStart(opponent.getCurrentXp());
        
        rivalry.setStartDate(LocalDateTime.now());
        rivalry.setEndDate(LocalDateTime.now().plusDays(3));
        rivalry.setStatus(RivalryStatus.PENDING);

        return rivalryRepository.save(rivalry);
    }

    public List<Ryvalry> getUserRivalries(String userId) {
        return rivalryRepository.findAll().stream()
                .filter(r -> r.getChallenger().getId().equals(userId) || r.getOpponent().getId().equals(userId))
                .collect(Collectors.toList());
    }

    public void updateRivalryStatus(String rivalryId, RivalryStatus newStatus) {
        Ryvalry rivalry = rivalryRepository.findById(rivalryId)
                .orElseThrow(() -> new RuntimeException("Rivalidad no encontrada"));
        
        rivalry.setStatus(newStatus);
        
        if (newStatus == RivalryStatus.FINISHED) {
            String winnerId = calculateWinner(rivalry);
            rivalry.setWinnerId(winnerId);
        }
        
        rivalryRepository.save(rivalry);
    }

    private String calculateWinner(Ryvalry rivalry) {
        int challengerGainedXp = (rivalry.getChallenger().getCurrentXp() != null ? rivalry.getChallenger().getCurrentXp() : 0) - rivalry.getChallengerXpStart();
        int opponentGainedXp = (rivalry.getOpponent().getCurrentXp() != null ? rivalry.getOpponent().getCurrentXp() : 0) - rivalry.getOpponentXpStart();

        if (challengerGainedXp > opponentGainedXp) {
            return rivalry.getChallenger().getId();
        } else if (opponentGainedXp > challengerGainedXp) {
            return rivalry.getOpponent().getId();
        }
        return "DRAW";
    }
}
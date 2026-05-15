package com.example.EthosPath.model.entity;

import com.example.EthosPath.repository.RivalryRepository;
import com.example.EthosPath.services.RivalryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RivalryScheduler {

    @Autowired
    private RivalryRepository rivalryRepository;

    @Autowired
    private RivalryService rivalryService;

    @Scheduled(cron = "0 0 * * * *")
    public void checkExpiredRivalries() {
        List<Ryvalry> activeRivalries = rivalryRepository.findByStatus(RivalryStatus.ACTIVE);
        LocalDateTime now = LocalDateTime.now();

        for (Ryvalry rivalry : activeRivalries) {
            if (now.isAfter(rivalry.getEndDate())) {
                rivalryService.updateRivalryStatus(rivalry.getId(), RivalryStatus.FINISHED);
            }
        }
    }
}
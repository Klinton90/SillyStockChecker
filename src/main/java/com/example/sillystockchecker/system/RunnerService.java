package com.example.sillystockchecker.system;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RunnerService {

    private final CostcoService costcoService;
    private final BotService botService;

    @Autowired
    public RunnerService(CostcoService costcoService, BotService botService) {
        this.costcoService = costcoService;
        this.botService = botService;
    }

    @Scheduled(initialDelay = 5000, fixedDelay = 60000)
    public void run() {
        log.info("Starting 'run'");
//        boolean result = costcoService.checkUrl("northwood-dualdrive-tandem-bicycle.product.100043708.html");
//        boolean result = costcoService.checkUrl("30.48-cm-(12-in.)-infinity-totter-children-balance-bicycle.product.100284230.html");
        boolean result = costcoService.checkUrl("northrock-xc29-73.6-cm-(29-in.)-mountain-bike.product.100674055.html");
        log.info("received result is: " + result);
        if (result) {
            botService.sendMessages();
        }
    }
}

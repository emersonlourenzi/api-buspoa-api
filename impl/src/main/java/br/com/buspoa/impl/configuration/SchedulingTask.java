package br.com.buspoa.impl.configuration;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SchedulingTask {

    @Scheduled(cron = "0 0 2 * * ?", zone = "America/Sao_Paulo")
    public void scheduleFutureTask() {
        System.out.println("ATUALIZAÇÃO DIARIA . . . ");
    }

}

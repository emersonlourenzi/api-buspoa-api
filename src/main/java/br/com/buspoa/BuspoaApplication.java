package br.com.buspoa;

import br.com.buspoa.impl.configuration.SchedulingTask;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BuspoaApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuspoaApplication.class, args);

        SchedulingTask schedulingTask = new SchedulingTask();
        schedulingTask.scheduleFutureTask();
    }

}

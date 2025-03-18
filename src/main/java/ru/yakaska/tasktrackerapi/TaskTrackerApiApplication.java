package ru.yakaska.tasktrackerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskTrackerApiApplication {

    public static void main(String[] args) {

        System.out.println("task-tracker-api");

        SpringApplication.run(TaskTrackerApiApplication.class, args);
    }

}

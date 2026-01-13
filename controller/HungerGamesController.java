// src/controller/HungerGamesController.java
package controller;

import model.Tribut;
import service.HungerGamesService;

import java.io.IOException;
import java.util.List;

public class HungerGamesController {
    private HungerGamesService service;

    public HungerGamesController(HungerGamesService service) {
        this.service = service;
    }

    public void run() {
        try {
            // Task 1: Load and Print Stats
            service.loadData("src/resources/tributes.json", "src/resources/events.json", "src/resources/gifts.json");

            System.out.println("Tributes loaded: " + service.getAllTributes().size());
            System.out.println("Events loaded: " + service.getEventsCount());
            System.out.println("Gifts loaded: " + service.getGiftsCount());

            for (Tribut t : service.getAllTributes()) {
                System.out.println(t);
            }
        } catch (IOException e) {
            System.err.println("Failed to load data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

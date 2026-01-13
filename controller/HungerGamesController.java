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

        handleDistrictFilter();
    }

    private void handleDistrictFilter() {
        System.out.println("\nSorted Tributes:");
        List<Tribut> sorted = service.getSortedTributes();
        sorted.forEach(System.out::println);

        try {
            service.exportSortedTributes("tributes_sorted.txt");
            System.out.println("\nTask 4: tributes_sorted.txt created.");
        } catch (IOException e) {
            System.err.println("Failed to export sorted tributes: " + e.getMessage());
        }
    }
}

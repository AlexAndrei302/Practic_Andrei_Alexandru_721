package service;

import model.*;
import repository.HungerGamesRepository;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HungerGamesService {
    private HungerGamesRepository repository;
    private List<Tribut> tributes;
    private List<Ereignis> events;
    private List<SponsorGeschenk> gifts;

    public HungerGamesService(HungerGamesRepository repository) {
        this.repository = repository;
    }

    public void loadData(String tributesFile, String eventsFile, String giftsFile) throws IOException {
        this.tributes = repository.loadTributes(tributesFile);
        this.events = repository.loadEvents(eventsFile);
        this.gifts = repository.loadGifts(giftsFile);
    }

    public List<Tribut> getAllTributes() {
        return tributes;
    }

    public int getEventsCount() {
        return events.size();
    }

    public int getGiftsCount() {
        return gifts.size();
    }


    public List<Tribut> filterTributes(int district, Status status) {
        return tributes.stream()
                .filter(t -> t.getDistrikt() == district && t.getStatus() == status)
                .collect(Collectors.toList());
    }
    public List<Tribut> getSortedTributes() {
        return tributes.stream()
                .sorted(Comparator.comparingInt(Tribut::getSkillLevel).reversed()
                        .thenComparing(Tribut::getName))
                .collect(Collectors.toList());
    }
    public void exportSortedTributes(String filename) throws IOException {
        List<Tribut> sorted = getSortedTributes();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Tribut t : sorted) {
                writer.write(t.toString());
                writer.newLine();
            }
        }
    }
}
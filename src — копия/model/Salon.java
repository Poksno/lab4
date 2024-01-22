package model;

import java.util.ArrayList;
import java.util.List;

public class Salon {
    private int meetingRoomCapacity;
    private boolean isMeetingAreaAvailable;
    private List<Traveler> participants;
    private String[] amenities;
    private int numberOfSeats;
    public Salon(int numberOfSeats, boolean isMeetingAreaAvailable, String[] amenities,int meetingRoomCapacity) {
        this.numberOfSeats = numberOfSeats;
        this.isMeetingAreaAvailable = isMeetingAreaAvailable;
        this.amenities = amenities;
        this.meetingRoomCapacity = meetingRoomCapacity;
        this.participants = new ArrayList<>();
    }

    public boolean addParticipant(Traveler traveler) {
        if (!isMeetingAreaAvailable) {
            System.out.println("Встреча недоступна.");
            return false;
        }

        if (participants.size() < meetingRoomCapacity) {
            participants.add(traveler);
            System.out.println(traveler.getName() + " добавлен в список участников.");
            return true;
        } else {
            System.out.println("Нет свободных мест для добавления " + traveler.getName());
            return false;
        }
    }

    public void replaceParticipant(Traveler oldTraveler, Traveler newTraveler) {
        if (participants.contains(oldTraveler)) {
            int index = participants.indexOf(oldTraveler);
            participants.set(index, newTraveler);
            System.out.println(oldTraveler.getName() + " был заменен на " + newTraveler.getName());
        } else {
            System.out.println(oldTraveler.getName() + " не найден среди участников.");
        }
    }
    public void conductMeeting() {
        if (isMeetingAreaAvailable) {
            System.out.println("Встреча в салоне началась.");
        } else {
            System.out.println("Встреча невозможна, нет доступной зоны для встреч.");
        }
    }

    public void displayParticipants() {
        if (participants.isEmpty()) {
            System.out.println("Встреча пуста. Нет участников.");
            return;
        }

        System.out.println("Список участников встречи:");
        for (Traveler participant : participants) {
            System.out.println(participant.getName());
        }
    }
    public void displayAmenities() {
        System.out.println("Удобства салона: ");
        for (String amenity : amenities) {
            System.out.println(amenity);
        }
    }


    public boolean isMeetingAreaAvailable() {
        return isMeetingAreaAvailable;
    }

    public void setMeetingAreaAvailable(boolean isMeetingAreaAvailable) {
        this.isMeetingAreaAvailable = isMeetingAreaAvailable;
    }


}
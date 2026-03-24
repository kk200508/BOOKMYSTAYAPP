/**
 * Use Case 5 – Booking Request (First Come First Served)
 * Demonstrates Queue data structure to handle booking
 * requests fairly in Book My Stay System.
 *
 * @author Student
 * @version 5.1
 */

import java.util.LinkedList;
import java.util.Queue;

// Reservation Class (Guest Booking Intent)
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public void displayReservation() {
        System.out.println("Guest : " + guestName +
                " | Requested Room : " + roomType);
    }
}

// Booking Queue Manager
class BookingRequestQueue {

    private Queue<Reservation> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add request (enqueue)
    public void addRequest(Reservation r) {
        queue.add(r);
        System.out.println("Request Added to Queue");
    }

    // Display queue
    public void displayQueue() {

        System.out.println("\n--- Current Booking Queue ---");

        for (Reservation r : queue) {
            r.displayReservation();
        }
    }
}

// Main Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Booking Request Queue");
        System.out.println("Version : 5.1");
        System.out.println("========================");

        BookingRequestQueue manager =
                new BookingRequestQueue();

        // Guest requests
        manager.addRequest(
                new Reservation("Arun", "Single Room"));

        manager.addRequest(
                new Reservation("Priya", "Double Room"));

        manager.addRequest(
                new Reservation("Kiran", "Suite Room"));

        manager.displayQueue();
    }
}

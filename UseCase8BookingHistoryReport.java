/**
 * Use Case 8 – Booking History & Reporting
 * Demonstrates historical storage of confirmed bookings
 * using List and generation of simple reports.
 *
 * @author Student
 * @version 8.1
 */

import java.util.*;

// Reservation Class (Confirmed Booking)
class Reservation {

    private String reservationId;
    private String guestName;
    private String roomType;

    public Reservation(String reservationId,
                       String guestName,
                       String roomType) {

        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void display() {
        System.out.println("Reservation ID : " + reservationId +
                " | Guest : " + guestName +
                " | Room : " + roomType);
    }
}

// Booking History Storage
class BookingHistory {

    private List<Reservation> history;

    public BookingHistory() {
        history = new ArrayList<>();
    }

    public void addReservation(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getHistory() {
        return history;
    }
}

// Reporting Service
class BookingReportService {

    private BookingHistory history;

    public BookingReportService(BookingHistory history) {
        this.history = history;
    }

    public void displayAllBookings() {

        System.out.println("\n--- Booking History ---");

        for (Reservation r : history.getHistory()) {
            r.display();
        }
    }

    public void generateRoomTypeSummary() {

        System.out.println("\n--- Room Type Report ---");

        Map<String, Integer> countMap =
                new HashMap<>();

        for (Reservation r : history.getHistory()) {
            countMap.put(
                    r.getRoomType(),
                    countMap.getOrDefault(
                            r.getRoomType(), 0) + 1);
        }

        for (Map.Entry<String, Integer> e :
                countMap.entrySet()) {

            System.out.println(e.getKey()
                    + " Bookings : "
                    + e.getValue());
        }
    }
}

// Main Class
public class UseCase8BookingHistoryReport {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Booking History System");
        System.out.println("Version : 8.1");
        System.out.println("========================");

        BookingHistory history =
                new BookingHistory();

        history.addReservation(
                new Reservation("RES1",
                        "Arun", "Single"));

        history.addReservation(
                new Reservation("RES2",
                        "Priya", "Double"));

        history.addReservation(
                new Reservation("RES3",
                        "Kiran", "Single"));

        BookingReportService report =
                new BookingReportService(history);

        report.displayAllBookings();
        report.generateRoomTypeSummary();
    }
}

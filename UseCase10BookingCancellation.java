/**
 * Use Case 10 – Booking Cancellation & Inventory Rollback
 * Demonstrates safe cancellation using Stack (LIFO rollback)
 * and restoring inventory consistency.
 *
 * @author Student
 * @version 10.1
 */

import java.util.*;

// Booking Record
class BookingRecord {

    String reservationId;
    String roomType;
    String roomId;

    public BookingRecord(String reservationId,
                         String roomType,
                         String roomId) {

        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}

// Cancellation Service
class CancellationService {

    private Map<String, BookingRecord> confirmedBookings;
    private Map<String, Integer> inventory;
    private Stack<String> rollbackStack;

    public CancellationService() {

        confirmedBookings = new HashMap<>();
        inventory = new HashMap<>();
        rollbackStack = new Stack<>();

        inventory.put("Single", 0);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);

        // already confirmed booking
        confirmedBookings.put(
                "RES1",
                new BookingRecord(
                        "RES1", "Single", "Single-A101"));
    }

    public void cancelBooking(String reservationId) {

        System.out.println("\nCancellation Request : "
                + reservationId);

        if (!confirmedBookings.containsKey(reservationId)) {
            System.out.println("Cancellation Failed : "
                    + "Invalid Reservation");
            return;
        }

        BookingRecord record =
                confirmedBookings.remove(reservationId);

        rollbackStack.push(record.roomId);

        inventory.put(record.roomType,
                inventory.get(record.roomType) + 1);

        System.out.println("Cancellation Successful");
        System.out.println("Released Room ID : "
                + record.roomId);
    }

    public void displayState() {

        System.out.println("\nCurrent Inventory : "
                + inventory);

        System.out.println("Rollback Stack : "
                + rollbackStack);
    }
}

// Main Class
public class UseCase10BookingCancellation {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Cancellation Module");
        System.out.println("Version : 10.1");
        System.out.println("========================");

        CancellationService service =
                new CancellationService();

        service.cancelBooking("RES1");
        service.cancelBooking("RES1"); // duplicate
        service.cancelBooking("RES2"); // invalid

        service.displayState();
    }
}

/**
 * Use Case 6 – Reservation Confirmation & Room Allocation
 * Demonstrates safe room allocation using Queue, HashMap and Set
 * to prevent double booking in Book My Stay System.
 *
 * @author Student
 * @version 6.1
 */

import java.util.*;

// Reservation (Booking Request)
class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {

    private HashMap<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 1);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public void reduceRoom(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory : " + inventory);
    }
}

// Booking Service (Allocation Logic)
class BookingService {

    private Queue<Reservation> queue;
    private InventoryService inventoryService;

    // Map room type → allocated room IDs
    private HashMap<String, Set<String>> allocatedRooms;

    public BookingService(Queue<Reservation> queue,
                          InventoryService inventoryService) {

        this.queue = queue;
        this.inventoryService = inventoryService;
        allocatedRooms = new HashMap<>();
    }

    public void processBookings() {

        System.out.println("\n--- Processing Bookings ---");

        while (!queue.isEmpty()) {

            Reservation r = queue.poll();

            if (inventoryService.getAvailability(r.roomType) > 0) {

                String roomId =
                        r.roomType + "-" + UUID.randomUUID()
                                .toString().substring(0, 4);

                allocatedRooms
                        .computeIfAbsent(r.roomType,
                                k -> new HashSet<>())
                        .add(roomId);

                inventoryService.reduceRoom(r.roomType);

                System.out.println("Booking Confirmed");
                System.out.println("Guest : " + r.guestName);
                System.out.println("Allocated Room ID : " + roomId);
                System.out.println();

            } else {
                System.out.println("Booking Failed (No Availability)");
                System.out.println("Guest : " + r.guestName);
                System.out.println();
            }
        }
    }
}

// Main Class
public class UseCase6RoomAllocationService {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Room Allocation System");
        System.out.println("Version : 6.1");
        System.out.println("========================");

        Queue<Reservation> queue = new LinkedList<>();

        queue.add(new Reservation("Arun", "Single"));
        queue.add(new Reservation("Priya", "Single"));
        queue.add(new Reservation("Kiran", "Single")); // should fail
        queue.add(new Reservation("Riya", "Suite"));

        InventoryService inventory = new InventoryService();

        BookingService booking =
                new BookingService(queue, inventory);

        booking.processBookings();

        inventory.displayInventory();
    }
}

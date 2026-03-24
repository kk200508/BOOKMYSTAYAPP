/**
 * Use Case 9 – Error Handling & Validation
 * Demonstrates input validation using custom exceptions
 * to prevent invalid booking and negative inventory.
 *
 * @author Student
 * @version 9.1
 */

import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}

// Inventory Service
class InventoryService {

    private Map<String, Integer> inventory;

    public InventoryService() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
        inventory.put("Double", 1);
        inventory.put("Suite", 0);
    }

    public void validateRoomType(String type)
            throws InvalidBookingException {

        if (!inventory.containsKey(type)) {
            throw new InvalidBookingException(
                    "Invalid Room Type : " + type);
        }
    }

    public void validateAvailability(String type)
            throws InvalidBookingException {

        if (inventory.get(type) <= 0) {
            throw new InvalidBookingException(
                    "No rooms available for : " + type);
        }
    }

    public void allocateRoom(String type)
            throws InvalidBookingException {

        validateRoomType(type);
        validateAvailability(type);

        inventory.put(type,
                inventory.get(type) - 1);

        System.out.println("Room Allocated Successfully for "
                + type);
    }

    public void displayInventory() {
        System.out.println("Current Inventory : " + inventory);
    }
}

// Main Class
public class UseCase9ErrorHandlingValidation {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Validation Module");
        System.out.println("Version : 9.1");
        System.out.println("========================");

        InventoryService service =
                new InventoryService();

        String[] testBookings =
                {"Single", "Suite", "Deluxe", "Double"};

        for (String room : testBookings) {

            try {
                service.allocateRoom(room);
            }
            catch (InvalidBookingException e) {
                System.out.println("Booking Error : "
                        + e.getMessage());
            }
        }

        service.displayInventory();
    }
}

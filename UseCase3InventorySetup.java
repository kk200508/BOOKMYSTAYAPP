/**
 * Use Case 3 – Centralized Room Inventory Management
 * Demonstrates usage of HashMap for maintaining
 * centralized room availability in Book My Stay System.
 *
 * @author Student
 * @version 3.1
 */

import java.util.HashMap;
import java.util.Map;

// Inventory Class (Single Source of Truth)
class RoomInventory {

    private HashMap<String, Integer> inventory;

    // Constructor → initialize inventory
    public RoomInventory() {
        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("\n--- Current Room Inventory ---");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " Available : " + entry.getValue());
        }
    }
}

// Main Class
public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Centralized Inventory System");
        System.out.println("Version : 3.1");
        System.out.println("========================");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        // Updating example
        System.out.println("\nUpdating Double Room availability to 4...");
        inventory.updateAvailability("Double Room", 4);

        inventory.displayInventory();
    }
}

/**
 * Use Case 4 – Room Search & Availability Check
 * Demonstrates read-only search operation using centralized inventory
 * without modifying system state.
 *
 * @author Student
 * @version 4.1
 */

import java.util.HashMap;
import java.util.Map;

// ---------- Room Domain Model ----------
abstract class Room {

    protected String type;
    protected double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public void displayDetails() {
        System.out.println("Room Type : " + type);
        System.out.println("Price     : " + price);
    }
}

class SingleRoom extends Room {
    public SingleRoom() {
        super("Single Room", 2000);
    }
}

class DoubleRoom extends Room {
    public DoubleRoom() {
        super("Double Room", 3500);
    }
}

class SuiteRoom extends Room {
    public SuiteRoom() {
        super("Suite Room", 6000);
    }
}

// ---------- Centralized Inventory ----------
class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 0);   // unavailable room
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    public Map<String, Integer> getAllInventory() {
        return inventory;   // read-only usage in search
    }
}

// ---------- Search Service ----------
class RoomSearchService {

    private RoomInventory inventory;

    public RoomSearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    public void searchAvailableRooms() {

        System.out.println("\n--- Available Rooms ---");

        for (Map.Entry<String, Integer> entry :
                inventory.getAllInventory().entrySet()) {

            if (entry.getValue() > 0) {

                Room room = createRoom(entry.getKey());
                room.displayDetails();
                System.out.println("Available : " + entry.getValue());
                System.out.println();
            }
        }
    }

    private Room createRoom(String type) {

        if (type.equals("Single Room"))
            return new SingleRoom();
        else if (type.equals("Double Room"))
            return new DoubleRoom();
        else
            return new SuiteRoom();
    }
}

// ---------- Main Class ----------
public class UseCase4RoomSearch {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Room Search System");
        System.out.println("Version : 4.1");
        System.out.println("========================");

        RoomInventory inventory = new RoomInventory();

        RoomSearchService search =
                new RoomSearchService(inventory);

        search.searchAvailableRooms();
    }
}

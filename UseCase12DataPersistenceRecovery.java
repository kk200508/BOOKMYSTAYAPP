/**
 * Use Case 12 – Data Persistence & System Recovery
 * Demonstrates saving and restoring system state using
 * object serialization.
 *
 * @author Student
 * @version 12.1
 */

import java.io.*;
import java.util.*;

// Serializable System State
class SystemState implements Serializable {

    Map<String, Integer> inventory;
    List<String> bookingHistory;

    public SystemState(Map<String, Integer> inventory,
                       List<String> bookingHistory) {

        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }
}

// Persistence Service
class PersistenceService {

    private static final String FILE_NAME = "hotel_state.dat";

    // Save state
    public void save(SystemState state) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            out.writeObject(state);
            System.out.println("System state saved successfully");

        } catch (IOException e) {
            System.out.println("Error saving state");
        }
    }

    // Load state
    public SystemState load() {

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(FILE_NAME))) {

            SystemState state =
                    (SystemState) in.readObject();

            System.out.println("System state restored");
            return state;

        } catch (Exception e) {

            System.out.println(
                    "No previous state found → Starting fresh");

            Map<String, Integer> inventory =
                    new HashMap<>();
            inventory.put("Single", 2);
            inventory.put("Double", 1);

            List<String> history =
                    new ArrayList<>();

            return new SystemState(inventory, history);
        }
    }
}

// Main Class
public class UseCase12DataPersistenceRecovery {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Persistence Module");
        System.out.println("Version : 12.1");
        System.out.println("========================");

        PersistenceService service =
                new PersistenceService();

        // load previous state
        SystemState state = service.load();

        System.out.println("Inventory : "
                + state.inventory);
        System.out.println("History : "
                + state.bookingHistory);

        // simulate booking
        state.inventory.put("Single",
                state.inventory.get("Single") - 1);

        state.bookingHistory.add("RES1001");

        // save state
        service.save(state);
    }
}

/**
 * Use Case 11 – Concurrent Booking Simulation
 * Demonstrates thread-safe booking allocation using
 * synchronized critical sections.
 *
 * @author Student
 * @version 11.1
 */

import java.util.*;

// Reservation Request
class Reservation {

    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// Shared Inventory (Thread Safe)
class SharedInventory {

    private Map<String, Integer> inventory;

    public SharedInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 2);
    }

    // critical section
    public synchronized boolean allocateRoom(String type,
                                             String guest) {

        int available =
                inventory.getOrDefault(type, 0);

        if (available > 0) {

            inventory.put(type, available - 1);

            System.out.println(
                    Thread.currentThread().getName()
                            + " → Booking Confirmed for "
                            + guest);

            return true;
        }

        System.out.println(
                Thread.currentThread().getName()
                        + " → Booking Failed for "
                        + guest);

        return false;
    }

    public void displayInventory() {
        System.out.println("\nFinal Inventory : "
                + inventory);
    }
}

// Booking Processor Thread
class BookingProcessor extends Thread {

    private Queue<Reservation> queue;
    private SharedInventory inventory;

    public BookingProcessor(Queue<Reservation> queue,
                            SharedInventory inventory,
                            String name) {

        super(name);
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation r;

            synchronized (queue) {
                if (queue.isEmpty())
                    break;

                r = queue.poll();
            }

            inventory.allocateRoom(
                    r.roomType,
                    r.guestName);

            try {
                Thread.sleep(100);
            }
            catch (InterruptedException e) { }
        }
    }
}

// Main Class
public class UseCase11ConcurrentBookingSimulation {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Concurrent Booking Module");
        System.out.println("Version : 11.1");
        System.out.println("========================");

        Queue<Reservation> queue =
                new LinkedList<>();

        queue.add(new Reservation("Arun", "Single"));
        queue.add(new Reservation("Priya", "Single"));
        queue.add(new Reservation("Kiran", "Single"));

        SharedInventory inventory =
                new SharedInventory();

        Thread t1 =
                new BookingProcessor(queue,
                        inventory,
                        "Thread-1");

        Thread t2 =
                new BookingProcessor(queue,
                        inventory,
                        "Thread-2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }
        catch (InterruptedException e) { }

        inventory.displayInventory();
    }
}

/**
 * Use Case 2 – Basic Room Types & Static Availability
 * Demonstrates abstraction, inheritance and polymorphism
 * in Book My Stay Hotel Booking System.
 *
 * @author Student
 * @version 2.1
 */

// Abstract Class
abstract class Room {

    protected String roomType;
    protected int beds;
    protected double price;

    public Room(String roomType, int beds, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.price = price;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type : " + roomType);
        System.out.println("Beds      : " + beds);
        System.out.println("Price     : " + price);
    }
}

// Single Room Class
class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2000);
    }
}

// Double Room Class
class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 3500);
    }
}

// Suite Room Class
class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 6000);
    }
}

// Main Application Class
public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Hotel Booking System");
        System.out.println("Version : 2.1");
        System.out.println("========================");

        // Polymorphism
        Room r1 = new SingleRoom();
        Room r2 = new DoubleRoom();
        Room r3 = new SuiteRoom();

        // Static Availability Variables
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("\n--- Room Details ---");

        r1.displayRoomDetails();
        System.out.println("Available : " + singleAvailable);

        System.out.println();

        r2.displayRoomDetails();
        System.out.println("Available : " + doubleAvailable);

        System.out.println();

        r3.displayRoomDetails();
        System.out.println("Available : " + suiteAvailable);

    }
}

/**
 * Use Case 7 – Add-On Service Selection
 * Demonstrates mapping of reservation IDs to optional services
 * using Map and List without affecting core booking logic.
 *
 * @author Student
 * @version 7.1
 */

import java.util.*;

// Add-On Service Class
class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }

    public void displayService() {
        System.out.println(serviceName + " : " + cost);
    }
}

// Service Manager
class AddOnServiceManager {

    // reservationId → list of services
    private Map<String, List<AddOnService>> serviceMap;

    public AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    // attach service
    public void addService(String reservationId,
                           AddOnService service) {

        serviceMap
                .computeIfAbsent(reservationId,
                        k -> new ArrayList<>())
                .add(service);
    }

    // display services
    public void displayServices(String reservationId) {

        System.out.println("\nServices for Reservation : "
                + reservationId);

        List<AddOnService> list =
                serviceMap.getOrDefault(
                        reservationId,
                        new ArrayList<>());

        double total = 0;

        for (AddOnService s : list) {
            s.displayService();
            total += s.getCost();
        }

        System.out.println("Total Add-On Cost : " + total);
    }
}

// Main Class
public class UseCase7AddOnServiceSelection {

    public static void main(String[] args) {

        System.out.println("===== Book My Stay =====");
        System.out.println("Add-On Service Module");
        System.out.println("Version : 7.1");
        System.out.println("========================");

        AddOnServiceManager manager =
                new AddOnServiceManager();

        String reservationId = "RES101";

        manager.addService(reservationId,
                new AddOnService("Breakfast", 500));

        manager.addService(reservationId,
                new AddOnService("Airport Pickup", 1200));

        manager.addService(reservationId,
                new AddOnService("Extra Bed", 800));

        manager.displayServices(reservationId);
    }
}

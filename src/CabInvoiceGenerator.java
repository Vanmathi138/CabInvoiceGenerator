import java.util.*;

class Ride {
    double distance;
    double time;

    public Ride(double distance, double time) {
        this.distance = distance;
        this.time = time;
    }
}
class InvoiceSummary {
    private int totalRides;
    private double totalFare;
    private double averageFarePerRide;

    public InvoiceSummary(int totalRides, double totalFare) {
        this.totalRides = totalRides;
        this.totalFare = totalFare;
        this.averageFarePerRide = totalFare / totalRides;
    }

    @Override
    public String toString() {
        return "Invoice Summary:\n" +
                "Total Rides: " + totalRides + "\n" +
                "Total Fare: Rs. " + totalFare + "\n" +
                "Average Fare Per Ride: Rs. " + String.format("%.2f", averageFarePerRide);
    }
}

class RideRepository {
    private Map<String, List<Ride>> userRides = new HashMap<>();

    public void addRides(String userId, Ride[] rides) {
        userRides.put(userId, Arrays.asList(rides));
    }

    public Ride[] getRides(String userId) {
        List<Ride> rides = userRides.get(userId);
        return rides == null ? new Ride[0] : rides.toArray(new Ride[0]);
    }
}

class InvoiceService {
    private static final double COST_PER_KILOMETER = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    private RideRepository rideRepository;

    public InvoiceService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public double calculateFare(double distance, double time) {
        double totalFare = (distance * COST_PER_KILOMETER) + (time * COST_PER_MINUTE);
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}

public class CabInvoiceGenerator {
    public static void main(String[] args) {
        RideRepository repository = new RideRepository();
        InvoiceService invoiceService = new InvoiceService(repository);

        repository.addRides("user1", new Ride[] {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        });

        repository.addRides("user2", new Ride[] {
                new Ride(5.0, 10),
                new Ride(3.0, 5),
                new Ride(0.2, 2)
        });

        System.out.println("=== Invoice for user1 ===");
        System.out.println(invoiceService.getInvoiceSummary("user1"));

        System.out.println("\n=== Invoice for user2 ===");
        System.out.println(invoiceService.getInvoiceSummary("user2"));
    }
}

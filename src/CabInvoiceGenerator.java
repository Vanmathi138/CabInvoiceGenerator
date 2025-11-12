import java.util.*;

enum RideType {
    NORMAL(10.0, 1.0, 5.0),
    PREMIUM(15.0, 2.0, 20.0);

    public final double costPerKm;
    public final double costPerMinute;
    public final double minFare;

    RideType(double costPerKm, double costPerMinute, double minFare) {
        this.costPerKm = costPerKm;
        this.costPerMinute = costPerMinute;
        this.minFare = minFare;
    }
}

class Ride {
    double distance;
    double time;
    RideType rideType;

    public Ride(double distance, double time, RideType rideType) {
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
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
    private RideRepository rideRepository;

    public InvoiceService(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }
    public double calculateFare(Ride ride) {
        double totalFare = (ride.distance * ride.rideType.costPerKm) + (ride.time * ride.rideType.costPerMinute);
        return Math.max(totalFare, ride.rideType.minFare);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        Ride[] rides = rideRepository.getRides(userId);
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}
public class CabInvoiceGenerator {
    public static void main(String[] args) {
        RideRepository repository = new RideRepository();
        InvoiceService invoiceService = new InvoiceService(repository);

        repository.addRides("user1", new Ride[] {
                new Ride(2.0, 5, RideType.NORMAL),
                new Ride(0.1, 1, RideType.PREMIUM)
        });

        repository.addRides("user2", new Ride[] {
                new Ride(5.0, 10, RideType.PREMIUM),
                new Ride(3.0, 5, RideType.NORMAL),
                new Ride(0.2, 2, RideType.NORMAL)
        });

        System.out.println("=== Invoice for user1 ===");
        System.out.println(invoiceService.getInvoiceSummary("user1"));

        System.out.println("\n=== Invoice for user2 ===");
        System.out.println(invoiceService.getInvoiceSummary("user2"));
    }
}

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

public class CabInvoiceGenerator {

    private static final double COST_PER_KILOMETER = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;


    public double calculateFare(double distance, double time) {
        double totalFare = (distance * COST_PER_KILOMETER) + (time * COST_PER_MINUTE);
        return Math.max(totalFare, MINIMUM_FARE);
    }

    public InvoiceSummary calculateFareForMultipleRides(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }

    public static void main(String[] args) {
        CabInvoiceGenerator invoice = new CabInvoiceGenerator();

        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(5.0, 10)
        };

        InvoiceSummary summary = invoice.calculateFareForMultipleRides(rides);
        System.out.println(summary);
    }
}

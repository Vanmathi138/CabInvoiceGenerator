class Ride {
    double distance;
    double time;

    public Ride(double distance, double time) {
        this.distance = distance;
        this.time = time;
    }
}

public class CabInvoiceGenerator {
    private static final double COST_PER_KILOMETER = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    //calculate fare
    public double calculateFare(double distance, double time) {
        double totalFare = (distance * COST_PER_KILOMETER) + (time * COST_PER_MINUTE);
        return Math.max(totalFare, MINIMUM_FARE);
    }

    // Step 2
    public double calculateFareForMultipleRides(Ride[] rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.distance, ride.time);
        }
        return totalFare;
    }

    public static void main(String[] args) {
        CabInvoiceGenerator invoice = new CabInvoiceGenerator();

        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1),
                new Ride(5.0, 10)
        };

        double totalFare = invoice.calculateFareForMultipleRides(rides);
        System.out.println("Total Fare for all rides: Rs. " + totalFare);
    }
}

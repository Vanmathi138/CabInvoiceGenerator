public class CabInvoiceGenerator {
    private static final double COST_PER_KILOMETER = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    //calculate fare
    public double calculateFare(double distance, double time) {
        double totalFare = (distance * COST_PER_KILOMETER) + (time * COST_PER_MINUTE);
        return Math.max(totalFare, MINIMUM_FARE);
    }
    public static void main(String[] args) {
        CabInvoiceGenerator invoice = new CabInvoiceGenerator();

        double fare1 = invoice.calculateFare(2.0, 5); // Example 1
        double fare2 = invoice.calculateFare(0.1, 1); // Example 2 (Minimum fare case)

        System.out.println("Fare 1: Rs. " + fare1);
        System.out.println("Fare 2: Rs. " + fare2);
    }
}

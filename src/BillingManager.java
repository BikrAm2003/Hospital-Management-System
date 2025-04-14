import java.sql.*;
import java.util.Scanner;

public class BillingManager {
    public void generateBill() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Appointment ID: ");
            int appointmentId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Income Date (YYYY-MM-DD): ");
            String incomeDate = scanner.nextLine();
            System.out.print("Enter Amount: ");
            double amount = scanner.nextDouble();

            String sql = "INSERT INTO billing (appointment_id, income_date, amount) VALUES (?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, appointmentId);
                pstmt.setDate(2, Date.valueOf(incomeDate));
                pstmt.setDouble(3, amount);
                pstmt.executeUpdate();
                System.out.println("Bill generated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewBills() {
        String sql = "SELECT b.id, a.id AS appointment_id, b.income_date, b.amount " +
                "FROM billing b " +
                "JOIN appointments a ON b.appointment_id = a.id";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Billing List:");
            while (rs.next()) {
                System.out.printf("Bill ID: %d, Appointment ID: %d, Date: %s, Amount: %.2f%n",
                        rs.getInt("id"), rs.getInt("appointment_id"),
                        rs.getDate("income_date"), rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

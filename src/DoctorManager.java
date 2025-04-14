import java.sql.*;
import java.util.Scanner;

public class DoctorManager {
    public void addDoctor() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Doctor Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Specialty: ");
            String specialty = scanner.nextLine();

            String sql = "INSERT INTO doctors (name, specialty) VALUES (?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, specialty);
                pstmt.executeUpdate();
                System.out.println("Doctor added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewDoctors() {
        String sql = "SELECT * FROM doctors";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Doctor List:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Specialty: %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("specialty"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

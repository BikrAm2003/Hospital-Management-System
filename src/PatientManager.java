import java.sql.*;
import java.util.Scanner;

public class PatientManager {
    public void addPatient() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Patient Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Age: ");
            int age = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Gender: ");
            String gender = scanner.nextLine();

            String sql = "INSERT INTO patients (name, age, gender) VALUES (?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setInt(2, age);
                pstmt.setString(3, gender);
                pstmt.executeUpdate();
                System.out.println("Patient added successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewPatients() {
        String sql = "SELECT * FROM patients";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Patient List:");
            while (rs.next()) {
                System.out.printf("ID: %d, Name: %s, Age: %d, Gender: %s%n",
                        rs.getInt("id"), rs.getString("name"),
                        rs.getInt("age"), rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

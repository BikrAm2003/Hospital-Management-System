import java.sql.*;
import java.util.Scanner;

public class AppointmentManager {
    public void scheduleAppointment() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter Patient ID: ");
            int patientId = scanner.nextInt();
            System.out.print("Enter Doctor ID: ");
            int doctorId = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            System.out.print("Enter Appointment Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();

            String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_date) VALUES (?, ?, ?)";

            try (Connection conn = DatabaseConnection.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, patientId);
                pstmt.setInt(2, doctorId);
                pstmt.setDate(3, Date.valueOf(date));
                pstmt.executeUpdate();
                System.out.println("Appointment scheduled successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void viewAppointments() {
        String sql = "SELECT a.id, p.name AS patient_name, d.name AS doctor_name, a.appointment_date " +
                "FROM appointments a " +
                "JOIN patients p ON a.patient_id = p.id " +
                "JOIN doctors d ON a.doctor_id = d.id";

        try (Connection conn = DatabaseConnection.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Appointment List:");
            while (rs.next()) {
                System.out.printf("ID: %d, Patient: %s, Doctor: %s, Date: %s%n",
                        rs.getInt("id"), rs.getString("patient_name"),
                        rs.getString("doctor_name"), rs.getDate("appointment_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

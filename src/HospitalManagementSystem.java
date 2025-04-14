import java.util.Scanner;

public class HospitalManagementSystem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PatientManager patientManager = new PatientManager();
        DoctorManager doctorManager = new DoctorManager();
        AppointmentManager appointmentManager = new AppointmentManager();
        BillingManager billingManager = new BillingManager();

        while (true) {
            System.out.println("\n=== Hospital Management System ===");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patients");
            System.out.println("3. View Doctors");
            System.out.println("4. Book Appointment");
            System.out.println("5. Generate Bill");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    patientManager.addPatient();
                    break;
                case "2":
                    patientManager.viewPatients();
                    break;
                case "3":
                    doctorManager.viewDoctors();
                    break;
                case "4":
                    appointmentManager.scheduleAppointment();
                    break;
                case "5":
                    billingManager.generateBill();
                    break;
                case "6":
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

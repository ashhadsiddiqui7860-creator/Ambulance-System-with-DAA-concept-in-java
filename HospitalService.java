package ambsys;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalService {
    private List<Hospital> hospitals = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    public void addHospital() {
        System.out.print("Enter Hospital Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Location: ");
        String location = sc.nextLine();
        hospitals.add(new Hospital(name, location));
        System.out.println(" Hospital added successfully.");
    }

    public void viewHospitals() {
        if (hospitals.isEmpty()) {
            System.out.println("️ No hospitals found.");
            return;
        }
        System.out.println(" List of Hospitals:");
        for (Hospital h : hospitals) {
            System.out.println("- " + h.name + " at " + h.location);
        }
    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }
}

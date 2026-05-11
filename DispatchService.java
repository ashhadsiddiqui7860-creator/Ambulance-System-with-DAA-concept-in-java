package ambsys;

import java.util.Scanner;

public class DispatchService {
    private Scanner sc = new Scanner(System.in);
    private Graph graph;
    private AmbulanceService ambulanceService;

    public DispatchService(Graph graph, AmbulanceService ambulanceService) {
        this.graph = graph;
        this.ambulanceService = ambulanceService;
    }

    public void dispatch() {
        System.out.print("Enter Emergency Location: ");
        String location = sc.nextLine().toLowerCase();

        Ambulance amb = ambulanceService.findNearest(location, graph);

        if (amb != null) {
            ambulanceService.updateAvailability(amb.getId(), false);

            System.out.println("Ambulance " + amb.getId() +
                    " dispatched from " + amb.getCurrentLocation() +
                    " to " + location);
        } else {
            System.out.println("No ambulance available.");
        }
    }
}

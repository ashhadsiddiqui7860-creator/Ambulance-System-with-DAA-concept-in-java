package ambsys;

public class Ambulance {
    private String id;
    private String currentLocation;

    public Ambulance(String id, String location) {
        this.id = id;
        this.currentLocation = location;
    }

    public String getId() {
        return id;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }
}

package ambsys;

import java.sql.*;
import java.util.*;

public class AmbulanceService 
{
    private Scanner sc = new Scanner(System.in);

    public void addAmbulance() 
    {
        System.out.print("Enter Ambulance ID: ");
        String id = sc.nextLine();

        System.out.print("Enter Location: ");
        String location = sc.nextLine().toLowerCase();
        try 
            {
                Connection con = DBConnection.getConnection();

                String query = "INSERT INTO ambulance (id, location, available) VALUES (?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, id);
                ps.setString(2, location);
                ps.setBoolean(3, true);

                ps.executeUpdate();
                System.out.println("Ambulance added successfully");

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }

    public void viewAmbulances()
    {
        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM ambulance";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(
                        rs.getString("id") + " | " +
                                rs.getString("location") + " | " +
                                (rs.getBoolean("available") ? "Available" : "Busy")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Ambulance findNearest(String location, Graph graph) 
    {
        Ambulance nearest = null;
        int minDistance = Integer.MAX_VALUE;

        try 
        {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM ambulance WHERE available = true";
            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) 
            {
                String id = rs.getString("id");
                String currentLocation = rs.getString("location");

                int distance = graph.getDistance(currentLocation, location);

                if (distance < minDistance) 
                {
                    minDistance = distance;
                    nearest = new Ambulance(id, currentLocation);
                }
            }

        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return nearest;
    }

    public void updateAvailability(String id, boolean status) 
    {
        try 
        {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE ambulance SET available = ? WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setBoolean(1, status);
            ps.setString(2, id);

            ps.executeUpdate();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
}

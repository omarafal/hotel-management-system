package org.example.RoomsModule;
import org.example.AuthenticationModule.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomsManager {

    // Fetch all rooms (for manager dashboard)
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms";

        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        rs.getInt("is_available") == 1
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public List<Room> getRoomsByType(String type) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms WHERE room_type=?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);  // set parameter
            ResultSet rs = stmt.executeQuery();  // execute without passing sql

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        rs.getInt("is_available") == 1
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rooms;
    }

    // Fetch available rooms of a specific type (for receptionist)
    public List<Room> getAvailableRooms(String type) {
        List<Room> rooms = new ArrayList<>();
        String sql = "SELECT * FROM Rooms WHERE room_type=? AND is_available=1";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                rooms.add(new Room(
                        rs.getInt("room_id"),
                        rs.getString("room_number"),
                        rs.getString("room_type"),
                        rs.getDouble("price"),
                        true
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Assign a room to a resident (update availability)
    public Room assignRoomToResident(String roomType) {
        // Get all rooms of the given type
        List<Room> rooms = getRoomsByType(roomType);

        // Find the first available room
        for (Room room : rooms) {
            if (room.isAvailable()) {
                // Mark the room as occupied in the database
                String sqlUpdate = "UPDATE Rooms SET is_available=0 WHERE room_id=?";

                try (Connection conn = DatabaseConnection.connect();
                     PreparedStatement stmt = conn.prepareStatement(sqlUpdate)) {

                    stmt.setInt(1, room.getRoomId());
                    stmt.executeUpdate();

                    // Update the object state
                    room.setAvailable(false);

                    return room; // return the assigned room

                } catch (SQLException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        // No available rooms of this type
        return null;
    }


    // Release a room (on checkout)
    public boolean releaseRoom(String roomNumber) {
        String sql = "UPDATE Rooms SET is_available=1 WHERE room_number=?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, roomNumber);
            int rows = stmt.executeUpdate();
            return rows > 0; // true if release succeeded

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}


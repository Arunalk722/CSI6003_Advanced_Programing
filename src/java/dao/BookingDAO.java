package dao;

import model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    // ✅ **1. Create a New Booking (Returns Generated Booking ID)**
    public int createBooking(Booking booking) {
        int bookingId = -1;
        String query = "INSERT INTO bookings (customer_phone, source, destination, vehicle_id, driver_id, total_amount, is_paid) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, booking.getCustomerPhone());
            stmt.setString(2, booking.getSource());
            stmt.setString(3, booking.getDestination());
            stmt.setInt(4, booking.getVehicleId());
            stmt.setInt(5, booking.getDriverId());
            stmt.setFloat(6, booking.getTotalAmount());
            stmt.setBoolean(7, false); // Default: Not paid

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    bookingId = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error creating booking: " + e.getMessage());
        }
        return bookingId;
    }

    // ✅ **2. Get Booking by ID (For Payment Validation)**
    public Booking getBookingById(int bookingId) {
        String query = "SELECT * FROM bookings WHERE booking_id = ?";
        Booking booking = null;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("customer_phone"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),  // ✅ Fixed: Added missing driverId
                    rs.getFloat("total_amount"),
                    rs.getBoolean("is_paid")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error fetching booking: " + e.getMessage());
        }
        return booking;
    }

    // ✅ **3. Update Booking as Paid**
    public boolean markBookingAsPaid(int bookingId) {
        String query = "UPDATE bookings SET is_paid = TRUE WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error marking booking as paid: " + e.getMessage());
            return false;
        }
    }

    // ✅ **4. Delete a Booking (Admin Cancels)**
    public boolean deleteBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            return false;
        }
    }

    // ✅ **5. Auto-Cancel Unpaid Bookings After 2 Days**
    public void autoCancelUnpaidBookings() {
        String query = "DELETE FROM bookings WHERE is_paid = FALSE AND booking_date < NOW() - INTERVAL 2 DAY";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println(rowsDeleted + " unpaid bookings were automatically canceled.");
            }
        } catch (SQLException e) {
            System.err.println("Error auto-canceling bookings: " + e.getMessage());
        }
    }

    // ✅ **6. Get All Bookings (For Admin)**
    public List<Booking> getAllBookings() {
        String query = "SELECT * FROM bookings";
        List<Booking> bookingList = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Booking booking = new Booking(
                    rs.getInt("booking_id"),
                    rs.getString("source"),
                    rs.getString("destination"),
                    rs.getString("customer_phone"),
                    rs.getInt("vehicle_id"),
                    rs.getInt("driver_id"),
                    rs.getFloat("total_amount"),
                    rs.getBoolean("is_paid")
                );
                bookingList.add(booking);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching all bookings: " + e.getMessage());
        }
        return bookingList;
    }
}

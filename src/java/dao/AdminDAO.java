/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import java.sql.*;
//import model.Admin;
/**
 *
 * @author Aruna
 */
public class AdminDAO {
    
 public boolean validateAdmin(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ? AND role = 'admin'";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Returns true if admin exists

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ **2. Delete a User (Call Center Operator or Admin)**
    public boolean deleteUser(int userId) {
        String query = "DELETE FROM users WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, userId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ **3. Cancel a Booking**
    public boolean cancelBooking(int bookingId) {
        String query = "DELETE FROM bookings WHERE booking_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, bookingId);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ **4. View All Payments**
    public ResultSet getAllPayments() {
        String query = "SELECT * FROM payments";
        
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(query);
            
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
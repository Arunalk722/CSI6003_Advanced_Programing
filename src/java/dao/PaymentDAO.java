package dao;

import model.Payment;
import java.sql.*;

public class PaymentDAO {

    // ✅ 1. Validate Booking Before Payment
    public boolean validateBookingBeforePayment(int bookingId, String customerPhone) {
        String query = "SELECT * FROM bookings WHERE booking_id = ? AND customer_phone = ? AND is_paid = FALSE";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            stmt.setString(2, customerPhone);
            ResultSet rs = stmt.executeQuery();

            return rs.next(); // ✅ Returns true if booking is valid and unpaid

        } catch (SQLException e) {
            System.err.println("Error validating booking: " + e.getMessage());
            return false;
        }
    }

    // ✅ 2. Process Payment & Mark Booking as Paid
    public boolean processPayment(Payment payment) {
        if (!validateBookingBeforePayment(payment.getBookingId(), payment.getCustomerPhone())) {
            System.err.println("Error: Booking not found or already paid.");
            return false;
        }

        String paymentQuery = "INSERT INTO payments (booking_id, customer_phone, amount, payment_method, payment_date_time) VALUES (?, ?, ?, ?, NOW())";
        String updateBookingQuery = "UPDATE bookings SET is_paid = TRUE WHERE booking_id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmtPayment = conn.prepareStatement(paymentQuery);
             PreparedStatement stmtUpdateBooking = conn.prepareStatement(updateBookingQuery)) {

            stmtPayment.setInt(1, payment.getBookingId());
            stmtPayment.setString(2, payment.getCustomerPhone());
            stmtPayment.setFloat(3, payment.getAmount());
            stmtPayment.setString(4, payment.getPaymentMethod());

            int paymentInserted = stmtPayment.executeUpdate();

            if (paymentInserted > 0) {
                stmtUpdateBooking.setInt(1, payment.getBookingId());
                int bookingUpdated = stmtUpdateBooking.executeUpdate();
                return bookingUpdated > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error processing payment: " + e.getMessage());
        }
        return false;
    }

    // ✅ 3. Log Failed Payment
    public void logFailedPayment(int bookingId, String customerPhone, String reason) {
        String query = "INSERT INTO failed_payments (booking_id, customer_phone, reason, failure_time) VALUES (?, ?, ?, NOW())";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, bookingId);
            stmt.setString(2, customerPhone);
            stmt.setString(3, reason);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error logging failed payment: " + e.getMessage());
        }
    }
}

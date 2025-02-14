/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aruna
 */
import java.util.ArrayList;
import java.util.List;

public class NotificationService {
    private List<String> notifications = new ArrayList<>();

    public void sendBookingConfirmation(int bookingId, String customerPhone) {
        notifications.add("Booking " + bookingId + " confirmed for " + customerPhone);
        System.out.println("Booking confirmation sent to " + customerPhone);
    }

    public void sendPaymentFailure(int bookingId, String customerPhone) {
        notifications.add("Payment failed for Booking " + bookingId + ". Contact support.");
        System.out.println("Payment failure notification sent to " + customerPhone);
    }
}


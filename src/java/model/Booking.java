package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aruna
 */
public class Booking {
    private int bookingId;
    private String source;
    private String destination;
    private String customerPhone;
    private int vehicleId;
    private int driverId;
    private float totalAmount;
    private boolean isPaid;
    
    public Booking(int bookingId, String source, String destination, String customerPhone, int vehicleId, int driverId, float totalAmount) {
        this.bookingId = bookingId;
        this.source = source;
        this.destination = destination;
        this.customerPhone = customerPhone;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.totalAmount = totalAmount;
        this.isPaid = false;
    }

    public void confirmBooking() {
        System.out.println("Booking Confirmed for " + customerPhone);
    }

    public void autoCancel() {
        System.out.println("Booking " + bookingId + " is canceled due to non-payment.");
    }
}


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

    // ✅ **1. Default Constructor (Needed for DAO Operations)**
    public Booking() {}

    // ✅ **2. Constructor for New Booking (Without bookingId - Since DB Auto Generates)**
    public Booking(String source, String destination, String customerPhone, int vehicleId, int driverId, float totalAmount) {
        this.source = source;
        this.destination = destination;
        this.customerPhone = customerPhone;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.totalAmount = totalAmount;
        this.isPaid = false;
    }

    // ✅ **3. Constructor for Retrieving Existing Booking from DB**
    public Booking(int bookingId, String source, String destination, String customerPhone, int vehicleId, int driverId, float totalAmount, boolean isPaid) {
        this.bookingId = bookingId;
        this.source = source;
        this.destination = destination;
        this.customerPhone = customerPhone;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.totalAmount = totalAmount;
        this.isPaid = isPaid;
    }

    // ✅ **4. Getters & Setters (Required for DAO & Servlet Use)**
    public int getBookingId() { return bookingId; }
    public void setBookingId(int bookingId) { this.bookingId = bookingId; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }

    public String getCustomerPhone() { return customerPhone; }
    public void setCustomerPhone(String customerPhone) { this.customerPhone = customerPhone; }

    public int getVehicleId() { return vehicleId; }
    public void setVehicleId(int vehicleId) { this.vehicleId = vehicleId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public float getTotalAmount() { return totalAmount; }
    public void setTotalAmount(float totalAmount) { this.totalAmount = totalAmount; }

    public boolean isPaid() { return isPaid; }
    public void setPaid(boolean isPaid) { this.isPaid = isPaid; }

    // ✅ **5. Confirm Booking**
    public void confirmBooking() {
        System.out.println("Booking Confirmed for " + customerPhone);
    }

    // ✅ **6. Mark Booking as Paid**
    public void markAsPaid() {
        this.isPaid = true;
        System.out.println("Booking " + bookingId + " is marked as PAID.");
    }

    // ✅ **7. Auto-Cancel Booking (Simulated)**
    public void autoCancel() {
        if (!isPaid) {
            System.out.println("Booking " + bookingId + " is canceled due to non-payment.");
        }
    }
}
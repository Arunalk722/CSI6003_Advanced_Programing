/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aruna
 */
interface PaymentStrategy {
    void processPayment(float amount, String customerPhone);
}

class CreditCardPayment implements PaymentStrategy {
    public void processPayment(float amount, String customerPhone) {
        System.out.println("Payment of $" + amount + " via Credit Card for " + customerPhone + " processed.");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void processPayment(float amount, String customerPhone) {
        System.out.println("Payment of $" + amount + " via PayPal for " + customerPhone + " processed.");
    }
}


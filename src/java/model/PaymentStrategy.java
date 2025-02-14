package model;

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

//modle change
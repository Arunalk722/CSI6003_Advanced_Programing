/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aruna
 */
interface DiscountStrategy {
    float applyDiscount(float totalAmount);
}

class NoDiscount implements DiscountStrategy {
    public float applyDiscount(float totalAmount) {
        return totalAmount;
    }
}

class LoyaltyDiscount implements DiscountStrategy {
    public float applyDiscount(float totalAmount) {
        return totalAmount * 0.9f;  // 10% discount
    }
}


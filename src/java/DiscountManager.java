/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Aruna
 */
public class DiscountManager {
    private DiscountStrategy strategy;

    public DiscountManager(DiscountStrategy strategy) {
        this.strategy = strategy;
    }

    public float getFinalAmount(float totalAmount) {
        return strategy.applyDiscount(totalAmount);
    }
}

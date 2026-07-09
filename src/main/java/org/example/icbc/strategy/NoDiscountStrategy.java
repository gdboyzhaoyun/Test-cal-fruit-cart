package org.example.icbc.strategy;

import org.example.icbc.fruit.Fruit;

import java.math.BigDecimal;

/**
 * 无折扣策略
 * 
 * @author hzl
 * @date 2026-07-09
 */
public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public BigDecimal calculatePrice(Fruit fruit) {
        return fruit.calculateTotalPrice();
    }
}
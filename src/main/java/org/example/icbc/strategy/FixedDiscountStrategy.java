package org.example.icbc.strategy;

import org.example.icbc.fruit.Fruit;

import java.math.BigDecimal;

/**
 * 固定折扣策略
 * 支持设置固定的折扣率
 * 
 * @author hzl
 * @date 2026-07-09
 */
public class FixedDiscountStrategy implements DiscountStrategy {

    private final BigDecimal discountRate;

    public FixedDiscountStrategy(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public BigDecimal calculatePrice(Fruit fruit) {
        return fruit.calculateTotalPrice().multiply(discountRate);
    }
}
package org.example.icbc.strategy;

import org.example.icbc.fruit.Fruit;

import java.math.BigDecimal;

/**
 * 折扣策略接口
 * 定义折扣计算的策略模式
 * 
 * @author hzl
 * @date 2026-07-09
 */
public interface DiscountStrategy {

    /**
     * 计算折扣后的价格
     *
     * @param fruit 水果对象
     * @return 折后价格
     */
    BigDecimal calculatePrice(Fruit fruit);
}
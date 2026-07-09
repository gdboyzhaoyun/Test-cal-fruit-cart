package org.example.icbc.service;

import org.example.icbc.cart.ShoppingCart;
import org.example.icbc.fruit.Fruit;
import org.example.icbc.strategy.DiscountStrategy;

import java.math.BigDecimal;

/**
 * 购物车服务类
 * 提供计算顾客购买商品总价的功能
 * 支持面向对象设计，使用策略模式处理折扣
 * 
 * @author hzl
 * @date 2026-07-09
 */
public class ShoppingCartService {

    private static final BigDecimal FULL_REDUCTION_THRESHOLD = new BigDecimal("100");
    private static final BigDecimal FULL_REDUCTION_AMOUNT = new BigDecimal("10");

    /**
     * 使用购物车计算总价（无折扣）
     *
     * @param cart 购物车对象
     * @return 总价
     */
    public BigDecimal calculateTotalPrice(ShoppingCart cart) {
        return cart.calculateTotalPrice();
    }

    /**
     * 使用购物车计算总价（带折扣）
     *
     * @param cart 购物车对象
     * @return 折后总价
     */
    public BigDecimal calculateTotalPriceWithDiscount(ShoppingCart cart) {
        return cart.calculateTotalPriceWithDiscount();
    }

    /**
     * 使用购物车计算总价（支持满减活动）
     *
     * @param cart 购物车对象
     * @return 满减后的总价
     */
    public BigDecimal calculateTotalPriceWithFullReduction(ShoppingCart cart) {
        BigDecimal totalPrice = cart.calculateTotalPrice();
        if (totalPrice.compareTo(FULL_REDUCTION_THRESHOLD) >= 0) {
            return totalPrice.subtract(FULL_REDUCTION_AMOUNT);
        }
        return totalPrice;
    }

    /**
     * 为购物车中的特定水果类型设置折扣策略
     *
     * @param cart             购物车对象
     * @param fruitClass       水果类型
     * @param discountStrategy 折扣策略
     */
    public void setDiscountForFruit(ShoppingCart cart, Class<? extends Fruit> fruitClass, DiscountStrategy discountStrategy) {
        cart.setDiscountStrategy(fruitClass, discountStrategy);
    }
}
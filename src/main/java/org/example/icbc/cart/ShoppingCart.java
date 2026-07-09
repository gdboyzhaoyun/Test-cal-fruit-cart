package org.example.icbc.cart;

import org.example.icbc.fruit.Fruit;
import org.example.icbc.strategy.DiscountStrategy;
import org.example.icbc.strategy.NoDiscountStrategy;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车类
 * 支持添加任意水果，并为每种水果设置不同的折扣策略
 * 使用策略模式实现灵活的折扣计算，支持动态切换折扣策略
 * 
 * @author hzl
 * @date 2026-07-09
 */
public class ShoppingCart {

    /**
     * 购物车中的水果列表
     */
    private final List<Fruit> items;

    /**
     * 水果类型到折扣策略的映射
     * Key: 水果类的Class对象
     * Value: 对应的折扣策略
     */
    private final Map<Class<? extends Fruit>, DiscountStrategy> discountStrategies;

    /**
     * 构造函数
     * 初始化空的水果列表和折扣策略映射
     */
    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.discountStrategies = new HashMap<>();
    }

    /**
     * 向购物车添加一个水果
     *
     * @param fruit 要添加的水果对象
     */
    public void addItem(Fruit fruit) {
        items.add(fruit);
    }

    /**
     * 为特定水果类型设置折扣策略
     *
     * @param fruitClass       水果类型（如 Apple.class）
     * @param discountStrategy 折扣策略（如 FixedDiscountStrategy）
     */
    public void setDiscountStrategy(Class<? extends Fruit> fruitClass, DiscountStrategy discountStrategy) {
        discountStrategies.put(fruitClass, discountStrategy);
    }

    /**
     * 计算购物车中所有水果的总价（无折扣）
     * 使用流式计算，将所有水果的价格累加
     *
     * @return 总价，保留两位小数
     */
    public BigDecimal calculateTotalPrice() {
        return items.stream()
                .map(Fruit::calculateTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算购物车中所有水果的总价（带折扣）
     * 根据每种水果对应的折扣策略计算折后价格
     *
     * @return 折后总价，保留两位小数
     */
    public BigDecimal calculateTotalPriceWithDiscount() {
        return items.stream()
                .map(this::calculateItemPriceWithDiscount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 计算单个水果的折后价格
     * 如果该水果类型没有设置折扣策略，则使用无折扣策略
     *
     * @param fruit 水果对象
     * @return 折后价格，保留两位小数
     */
    private BigDecimal calculateItemPriceWithDiscount(Fruit fruit) {
        DiscountStrategy strategy = discountStrategies.getOrDefault(fruit.getClass(), new NoDiscountStrategy());
        return strategy.calculatePrice(fruit).setScale(2, RoundingMode.HALF_UP);
    }
}
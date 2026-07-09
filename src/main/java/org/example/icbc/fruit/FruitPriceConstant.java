package org.example.icbc.fruit;

import java.math.BigDecimal;

/**
 * 水果价格常量类
 * 定义所有水果的单价及相关错误消息常量
 */
public final class FruitPriceConstant {

    /**
     * 苹果单价（元/斤）
     */
    public static final BigDecimal APPLE_PRICE_PER_JIN = new BigDecimal("8");

    /**
     * 草莓单价（元/斤）
     */
    public static final BigDecimal STRAWBERRY_PRICE_PER_JIN = new BigDecimal("13");

    /**
     * 芒果单价（元/斤）
     */
    public static final BigDecimal MANGO_PRICE_PER_JIN = new BigDecimal("20");

    /**
     * 重量为负数时的错误消息
     */
    public static final String ERROR_NEGATIVE_WEIGHT = "斤数不能为负数";
}
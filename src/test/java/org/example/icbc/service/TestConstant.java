package org.example.icbc.service;

import java.math.BigDecimal;

/**
 * 测试常量类
 * 定义测试中使用的常量值，避免魔法值
 */
public final class TestConstant {

    // ==================== 满减活动常量 ====================

    /**
     * 9折折扣率
     */
    public static final BigDecimal DISCOUNT_90_PERCENT = new BigDecimal("0.9");

    /**
     * 8折折扣率
     */
    public static final BigDecimal DISCOUNT_80_PERCENT = new BigDecimal("0.8");

    /**
     * 7折折扣率
     */
    public static final BigDecimal DISCOUNT_70_PERCENT = new BigDecimal("0.7");

    // ==================== 预期结果常量 ====================

    /**
     * 预期结果：0元
     */
    public static final BigDecimal EXPECTED_ZERO = BigDecimal.ZERO;

    /**
     * 预期结果：70元
     */
    public static final BigDecimal EXPECTED_70 = new BigDecimal("70");

    /**
     * 预期结果：90元
     */
    public static final BigDecimal EXPECTED_90 = new BigDecimal("90");

    /**
     * 预期结果：99元
     */
    public static final BigDecimal EXPECTED_99 = new BigDecimal("99");

    /**
     * 预期结果：109元
     */
    public static final BigDecimal EXPECTED_109 = new BigDecimal("109");

    /**
     * 预期结果：110元
     */
    public static final BigDecimal EXPECTED_110 = new BigDecimal("110");

    /**
     * 预期结果：400元
     */
    public static final BigDecimal EXPECTED_400 = new BigDecimal("400");

    // ==================== 测试数据常量 ====================

    /**
     * 测试数据：负数斤数
     */
    public static final int NEGATIVE_WEIGHT = -1;

    /**
     * 测试数据：0斤
     */
    public static final int ZERO_WEIGHT = 0;

    /**
     * 测试数据：1斤
     */
    public static final int ONE_JIN = 1;

    /**
     * 测试数据：2斤
     */
    public static final int TWO_JIN = 2;

    /**
     * 测试数据：3斤
     */
    public static final int THREE_JIN = 3;

    /**
     * 测试数据：4斤
     */
    public static final int FOUR_JIN = 4;

    /**
     * 测试数据：5斤
     */
    public static final int FIVE_JIN = 5;

    /**
     * 测试数据：6斤
     */
    public static final int SIX_JIN = 6;

    /**
     * 测试数据：10斤
     */
    public static final int TEN_JIN = 10;
}
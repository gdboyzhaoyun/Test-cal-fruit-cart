package org.example.icbc.fruit;

import java.math.BigDecimal;

/**
 * 水果抽象类
 * 定义水果的基本属性和行为
 * 
 * @author hzl
 * @date 2026-07-09
 */
public abstract class Fruit {

    /**
     * 单价（元/斤）
     */
    protected final BigDecimal pricePerJin;

    /**
     * 重量（斤）
     */
    protected int weightInJin;

    /**
     * 构造函数
     *
     * @param pricePerJin 单价（元/斤）
     */
    protected Fruit(BigDecimal pricePerJin) {
        this.pricePerJin = pricePerJin;
        this.weightInJin = 0;
    }

    /**
     * 设置水果重量
     *
     * @param weightInJin 重量（斤），必须大于等于0
     * @throws IllegalArgumentException 如果重量为负数
     */
    public void setWeightInJin(int weightInJin) {
        if (weightInJin < 0) {
            throw new IllegalArgumentException(FruitPriceConstant.ERROR_NEGATIVE_WEIGHT);
        }
        this.weightInJin = weightInJin;
    }

    /**
     * 计算水果总价（无折扣）
     *
     * @return 总价（元）
     */
    public BigDecimal calculateTotalPrice() {
        return pricePerJin.multiply(BigDecimal.valueOf(weightInJin));
    }
}
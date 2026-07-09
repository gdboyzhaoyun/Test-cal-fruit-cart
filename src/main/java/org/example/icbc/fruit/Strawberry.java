package org.example.icbc.fruit;

/**
 * 草莓类
 * 继承自水果抽象类
 */
public class Strawberry extends Fruit {

    /**
     * 构造函数
     * 使用草莓的默认单价
     */
    public Strawberry() {
        super(FruitPriceConstant.STRAWBERRY_PRICE_PER_JIN);
    }
}
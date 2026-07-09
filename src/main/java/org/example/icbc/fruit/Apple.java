package org.example.icbc.fruit;

/**
 * 苹果类
 * 继承自水果抽象类
 */
public class Apple extends Fruit {

    /**
     * 构造函数
     * 使用苹果的默认单价
     */
    public Apple() {
        super(FruitPriceConstant.APPLE_PRICE_PER_JIN);
    }
}
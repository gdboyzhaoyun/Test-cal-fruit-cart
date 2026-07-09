package org.example.icbc.fruit;

/**
 * 芒果类
 * 继承自水果抽象类
 */
public class Mango extends Fruit {

    /**
     * 构造函数
     * 使用芒果的默认单价
     */
    public Mango() {
        super(FruitPriceConstant.MANGO_PRICE_PER_JIN);
    }
}
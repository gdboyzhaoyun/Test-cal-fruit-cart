package org.example.icbc.service;

import org.example.icbc.cart.ShoppingCart;
import org.example.icbc.fruit.Apple;
import org.example.icbc.fruit.Mango;
import org.example.icbc.fruit.Strawberry;
import org.example.icbc.strategy.FixedDiscountStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 购物车服务单元测试类（顾客C）
 * 覆盖购物车价格计算的各种折扣场景，确保折扣计算逻辑正确
 */
@DisplayName("购物车服务测试（顾客C-折扣场景）")
class ShoppingCartServiceCTest {

    private final ShoppingCartService shoppingCartService = new ShoppingCartService();

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，草莓打8折，总价应为111.2CNY")
    void testCalculateTotalPriceWithDiscount_Strawberry80Percent() {
        ShoppingCart cart = createDefaultCart();
        shoppingCartService.setDiscountForFruit(cart, Strawberry.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_80_PERCENT));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithDiscount(cart);

        printCartInfo("草莓打8折");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("111.20").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，苹果打9折，总价应为115CNY")
    void testCalculateTotalPriceWithDiscount_Apple90Percent() {
        ShoppingCart cart = createDefaultCart();
        shoppingCartService.setDiscountForFruit(cart, Apple.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_90_PERCENT));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithDiscount(cart);

        printCartInfo("苹果打9折");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("115.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，芒果打7折，总价应为107CNY")
    void testCalculateTotalPriceWithDiscount_Mango70Percent() {
        ShoppingCart cart = createDefaultCart();
        shoppingCartService.setDiscountForFruit(cart, Mango.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_70_PERCENT));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithDiscount(cart);

        printCartInfo("芒果打7折");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("107.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，苹果打9折、草莓打8折，总价应为107.2CNY")
    void testCalculateTotalPriceWithDiscount_MultipleDiscounts() {
        ShoppingCart cart = createDefaultCart();
        shoppingCartService.setDiscountForFruit(cart, Apple.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_90_PERCENT));
        shoppingCartService.setDiscountForFruit(cart, Strawberry.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_80_PERCENT));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithDiscount(cart);

        printCartInfo("苹果打9折，草莓打8折");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("107.20").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，苹果打9折、草莓打8折、芒果打7折，总价应为95.2CNY")
    void testCalculateTotalPriceWithDiscount_AllDiscounts() {
        ShoppingCart cart = createDefaultCart();
        shoppingCartService.setDiscountForFruit(cart, Apple.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_90_PERCENT));
        shoppingCartService.setDiscountForFruit(cart, Strawberry.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_80_PERCENT));
        shoppingCartService.setDiscountForFruit(cart, Mango.class, new FixedDiscountStrategy(TestConstant.DISCOUNT_70_PERCENT));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithDiscount(cart);

        printCartInfo("苹果打9折，草莓打8折，芒果打7折");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("95.20").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，无折扣，总价应为119CNY")
    void testCalculateTotalPriceWithDiscount_NoDiscount() {
        ShoppingCart cart = createDefaultCart();

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithDiscount(cart);

        printCartInfo("无折扣");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("119.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("反例：苹果斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPriceWithDiscount_NegativeAppleWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createApple(TestConstant.NEGATIVE_WEIGHT));
    }

    @Test
    @DisplayName("反例：草莓斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPriceWithDiscount_NegativeStrawberryWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createStrawberry(TestConstant.NEGATIVE_WEIGHT));
    }

    @Test
    @DisplayName("反例：芒果斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPriceWithDiscount_NegativeMangoWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createMango(TestConstant.NEGATIVE_WEIGHT));
    }

    /**
     * 创建默认购物车（5斤苹果、3斤草莓、2斤芒果）
     */
    private ShoppingCart createDefaultCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(createApple(TestConstant.FIVE_JIN));
        cart.addItem(createStrawberry(TestConstant.THREE_JIN));
        cart.addItem(createMango(TestConstant.TWO_JIN));
        return cart;
    }

    /**
     * 打印购物车信息
     */
    private void printCartInfo(String discountInfo) {
        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.FIVE_JIN + "斤、" + TestConstant.THREE_JIN + "斤、" + TestConstant.TWO_JIN + "斤");
        System.out.println("折扣：" + discountInfo);
    }

    private Apple createApple(int weight) {
        Apple apple = new Apple();
        apple.setWeightInJin(weight);
        return apple;
    }

    private Strawberry createStrawberry(int weight) {
        Strawberry strawberry = new Strawberry();
        strawberry.setWeightInJin(weight);
        return strawberry;
    }

    private Mango createMango(int weight) {
        Mango mango = new Mango();
        mango.setWeightInJin(weight);
        return mango;
    }
}
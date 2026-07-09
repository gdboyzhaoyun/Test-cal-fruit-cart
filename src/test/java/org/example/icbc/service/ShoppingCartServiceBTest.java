package org.example.icbc.service;

import org.example.icbc.cart.ShoppingCart;
import org.example.icbc.fruit.Apple;
import org.example.icbc.fruit.Mango;
import org.example.icbc.fruit.Strawberry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 购物车服务单元测试类（顾客B）
 */
@DisplayName("购物车服务测试（顾客B）")
class ShoppingCartServiceBTest {

    private final ShoppingCartService shoppingCartService = new ShoppingCartService();

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，总价应为119CNY")
    void testCalculateTotalPrice_NormalCase1() {
        ShoppingCart cart = createCart(TestConstant.FIVE_JIN, TestConstant.THREE_JIN, TestConstant.TWO_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.FIVE_JIN + "斤、" + TestConstant.THREE_JIN + "斤、" + TestConstant.TWO_JIN + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("119.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买0斤苹果、0斤草莓和0斤芒果，总价应为0CNY")
    void testCalculateTotalPrice_NormalCase2() {
        ShoppingCart cart = createCart(TestConstant.ZERO_WEIGHT, TestConstant.ZERO_WEIGHT, TestConstant.ZERO_WEIGHT);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.ZERO_WEIGHT + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_ZERO.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买10斤苹果、0斤草莓和0斤芒果，总价应为80CNY")
    void testCalculateTotalPrice_NormalCase3() {
        ShoppingCart cart = createCart(TestConstant.TEN_JIN, TestConstant.ZERO_WEIGHT, TestConstant.ZERO_WEIGHT);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.TEN_JIN + "斤、" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.ZERO_WEIGHT + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("80.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买0斤苹果、5斤草莓和0斤芒果，总价应为65CNY")
    void testCalculateTotalPrice_NormalCase4() {
        ShoppingCart cart = createCart(TestConstant.ZERO_WEIGHT, TestConstant.FIVE_JIN, TestConstant.ZERO_WEIGHT);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.FIVE_JIN + "斤、" + TestConstant.ZERO_WEIGHT + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("65.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买0斤苹果、0斤草莓和5斤芒果，总价应为100CNY")
    void testCalculateTotalPrice_NormalCase5() {
        ShoppingCart cart = createCart(TestConstant.ZERO_WEIGHT, TestConstant.ZERO_WEIGHT, TestConstant.FIVE_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.FIVE_JIN + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("100.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买3斤苹果、0斤草莓和4斤芒果，总价应为104CNY")
    void testCalculateTotalPrice_NormalCase6() {
        ShoppingCart cart = createCart(TestConstant.THREE_JIN, TestConstant.ZERO_WEIGHT, TestConstant.FOUR_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.THREE_JIN + "斤、" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.FOUR_JIN + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("104.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买0斤苹果、2斤草莓和3斤芒果，总价应为86CNY")
    void testCalculateTotalPrice_NormalCase7() {
        ShoppingCart cart = createCart(TestConstant.ZERO_WEIGHT, TestConstant.TWO_JIN, TestConstant.THREE_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.TWO_JIN + "斤、" + TestConstant.THREE_JIN + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("86.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("反例：苹果斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPrice_NegativeAppleWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createApple(TestConstant.NEGATIVE_WEIGHT));
    }

    @Test
    @DisplayName("反例：草莓斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPrice_NegativeStrawberryWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createStrawberry(TestConstant.NEGATIVE_WEIGHT));
    }

    @Test
    @DisplayName("反例：芒果斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPrice_NegativeMangoWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createMango(TestConstant.NEGATIVE_WEIGHT));
    }

    @Test
    @DisplayName("反例：苹果、草莓和芒果斤数都为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPrice_BothNegativeWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createApple(-TestConstant.FIVE_JIN));
    }

    private ShoppingCart createCart(int appleWeight, int strawberryWeight, int mangoWeight) {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(createApple(appleWeight));
        cart.addItem(createStrawberry(strawberryWeight));
        cart.addItem(createMango(mangoWeight));
        return cart;
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
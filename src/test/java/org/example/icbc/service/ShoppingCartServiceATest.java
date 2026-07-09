package org.example.icbc.service;

import org.example.icbc.cart.ShoppingCart;
import org.example.icbc.fruit.Apple;
import org.example.icbc.fruit.Strawberry;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 购物车服务单元测试类（顾客A）
 */
@DisplayName("购物车服务测试（顾客A）")
class ShoppingCartServiceATest {

    private final ShoppingCartService shoppingCartService = new ShoppingCartService();

    @Test
    @DisplayName("正例：购买5斤苹果和3斤草莓，总价应为79CNY")
    void testCalculateTotalPrice_NormalCase1() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(createApple(TestConstant.FIVE_JIN));
        cart.addItem(createStrawberry(TestConstant.THREE_JIN));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓分别" + TestConstant.FIVE_JIN + "斤、" + TestConstant.THREE_JIN + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("79.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买0斤苹果和0斤草莓，总价应为0CNY")
    void testCalculateTotalPrice_NormalCase2() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(createApple(TestConstant.ZERO_WEIGHT));
        cart.addItem(createStrawberry(TestConstant.ZERO_WEIGHT));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓分别" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.ZERO_WEIGHT + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_ZERO.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买10斤苹果和0斤草莓，总价应为80CNY")
    void testCalculateTotalPrice_NormalCase3() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(createApple(TestConstant.TEN_JIN));
        cart.addItem(createStrawberry(TestConstant.ZERO_WEIGHT));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓分别" + TestConstant.TEN_JIN + "斤、" + TestConstant.ZERO_WEIGHT + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("80.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买0斤苹果和5斤草莓，总价应为65CNY")
    void testCalculateTotalPrice_NormalCase4() {
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(createApple(TestConstant.ZERO_WEIGHT));
        cart.addItem(createStrawberry(TestConstant.FIVE_JIN));

        BigDecimal actualTotal = shoppingCartService.calculateTotalPrice(cart);

        System.out.println("顾客购买了苹果、草莓分别" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.FIVE_JIN + "斤");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(new BigDecimal("65.00").setScale(2, RoundingMode.HALF_UP), actualTotal);
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
    @DisplayName("反例：苹果和草莓斤数都为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPrice_BothNegativeWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createApple(-TestConstant.FIVE_JIN));
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
}
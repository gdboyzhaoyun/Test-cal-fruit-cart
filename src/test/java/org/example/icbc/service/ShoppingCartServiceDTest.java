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
 * 购物车服务单元测试类（顾客D）
 * 覆盖满减活动（满100减10）的各种场景
 */
@DisplayName("购物车服务测试（顾客D-满减活动）")
class ShoppingCartServiceDTest {

    private final ShoppingCartService shoppingCartService = new ShoppingCartService();

    @Test
    @DisplayName("边界值：购买刚好100元（精确），应减10元，总价为90CNY")
    void testCalculateTotalPriceWithFullReduction_Exactly100Exact() {
        ShoppingCart cart = createCart(TestConstant.SIX_JIN, TestConstant.FOUR_JIN, TestConstant.ZERO_WEIGHT);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithFullReduction(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.SIX_JIN + "斤、" + TestConstant.FOUR_JIN + "斤、" + TestConstant.ZERO_WEIGHT + "斤");
        System.out.println("原价：100 CNY，满100减10");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_90.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("边界值：购买超过100元，应减10元")
    void testCalculateTotalPriceWithFullReduction_Over100() {
        ShoppingCart cart = createCart(TestConstant.SIX_JIN, TestConstant.FOUR_JIN, TestConstant.ONE_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithFullReduction(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.SIX_JIN + "斤、" + TestConstant.FOUR_JIN + "斤、" + TestConstant.ONE_JIN + "斤");
        System.out.println("原价：120 CNY，满100减10");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_110.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("边界值：购买99元，未满100元不减，总价为99CNY")
    void testCalculateTotalPriceWithFullReduction_Below100() {
        ShoppingCart cart = createCart(TestConstant.FIVE_JIN, TestConstant.THREE_JIN, TestConstant.ONE_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithFullReduction(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.FIVE_JIN + "斤、" + TestConstant.THREE_JIN + "斤、" + TestConstant.ONE_JIN + "斤");
        System.out.println("原价：99 CNY，未满100不减");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_99.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买5斤苹果、3斤草莓和2斤芒果，总价119元满100减10，应为109CNY")
    void testCalculateTotalPriceWithFullReduction_NormalCase() {
        ShoppingCart cart = createCart(TestConstant.FIVE_JIN, TestConstant.THREE_JIN, TestConstant.TWO_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithFullReduction(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.FIVE_JIN + "斤、" + TestConstant.THREE_JIN + "斤、" + TestConstant.TWO_JIN + "斤");
        System.out.println("原价：119 CNY，满100减10");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_109.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买10斤苹果、10斤草莓和10斤芒果，总价410元满100减10，应为400CNY")
    void testCalculateTotalPriceWithFullReduction_HighAmount() {
        ShoppingCart cart = createCart(TestConstant.TEN_JIN, TestConstant.TEN_JIN, TestConstant.TEN_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithFullReduction(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.TEN_JIN + "斤、" + TestConstant.TEN_JIN + "斤、" + TestConstant.TEN_JIN + "斤");
        System.out.println("原价：410 CNY，满100减10");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_400.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买3斤苹果、2斤草莓和1斤芒果，总价70元未满100，应为70CNY")
    void testCalculateTotalPriceWithFullReduction_NoReduction() {
        ShoppingCart cart = createCart(TestConstant.THREE_JIN, TestConstant.TWO_JIN, TestConstant.ONE_JIN);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithFullReduction(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.THREE_JIN + "斤、" + TestConstant.TWO_JIN + "斤、" + TestConstant.ONE_JIN + "斤");
        System.out.println("原价：70 CNY，未满100不减");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_70.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("正例：购买0斤苹果、0斤草莓和0斤芒果，总价为0CNY")
    void testCalculateTotalPriceWithFullReduction_ZeroPurchase() {
        ShoppingCart cart = createCart(TestConstant.ZERO_WEIGHT, TestConstant.ZERO_WEIGHT, TestConstant.ZERO_WEIGHT);

        BigDecimal actualTotal = shoppingCartService.calculateTotalPriceWithFullReduction(cart);

        System.out.println("顾客购买了苹果、草莓、芒果分别" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.ZERO_WEIGHT + "斤、" + TestConstant.ZERO_WEIGHT + "斤");
        System.out.println("原价：0 CNY，未满100不减");
        System.out.println("总价为：" + actualTotal + " CNY");

        assertEquals(TestConstant.EXPECTED_ZERO.setScale(2, RoundingMode.HALF_UP), actualTotal);
    }

    @Test
    @DisplayName("反例：苹果斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPriceWithFullReduction_NegativeAppleWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createApple(TestConstant.NEGATIVE_WEIGHT));
    }

    @Test
    @DisplayName("反例：草莓斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPriceWithFullReduction_NegativeStrawberryWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createStrawberry(TestConstant.NEGATIVE_WEIGHT));
    }

    @Test
    @DisplayName("反例：芒果斤数为负数，应抛出IllegalArgumentException")
    void testCalculateTotalPriceWithFullReduction_NegativeMangoWeight() {
        assertThrows(IllegalArgumentException.class,
                () -> createMango(TestConstant.NEGATIVE_WEIGHT));
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
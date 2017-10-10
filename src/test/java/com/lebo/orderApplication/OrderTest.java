package com.lebo.orderApplication;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class OrderTest {
    @Test
    public void testGetOrderTotalCostOnEmptyOrder() throws Exception {
        Order testOrder = new Order();
        assertEquals(new BigDecimal(-1), testOrder.getOrderTotalCost());
    }

    @Test
    public void testGetOrderTotalCostOnDefinedOrderIntCounts() throws Exception {
        Order testOrder = new Order();
        List<Item> testItemList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Item temp = new Item();
            temp.setCost(new BigDecimal(i + 0.01));
            temp.setCount(new BigDecimal(i));
            testItemList.add(temp);
        }

        testOrder.setItems(testItemList);

        MathContext mc = new MathContext(2, RoundingMode.HALF_UP  );
        assertEquals(new BigDecimal(1.01 + 4.02 + 9.03).round(mc), testOrder.getOrderTotalCost().round(mc));
    }

    @Test
    public void testGetOrderTotalCostOnDefinedOrderDecimalCounts() throws Exception {
        Order testOrder = new Order();
        List<Item> testItemList = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            Item temp = new Item();
            temp.setCost(new BigDecimal(i + 0.01));
            temp.setCount(new BigDecimal(i + 0.3));
            testItemList.add(temp);
        }

        testOrder.setItems(testItemList);

        MathContext mc = new MathContext(2, RoundingMode.HALF_UP  );
        assertEquals(new BigDecimal(1.313 + 4.623 + 9.999).round(mc), testOrder.getOrderTotalCost().round(mc));
    }

}
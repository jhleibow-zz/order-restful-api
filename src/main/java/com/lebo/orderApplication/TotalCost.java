package com.lebo.orderApplication;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

/**
 * Simple JAXB class to represent total cost.
 *
 * COPYRIGHT (C) 2017 John Leibowitz. All Rights Reserved.
 * @author John Leibowitz
 * @version 1.00
 */

@XmlRootElement(name="total_cost")
@XmlAccessorType(XmlAccessType.FIELD)
public class TotalCost {

    @XmlElement
    private BigDecimal amount;

    @XmlAttribute
    private int order_id;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}

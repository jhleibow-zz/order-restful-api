package com.lebo.orderApplication;

import com.lebo.orderDatabase.DatabaseManager;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order
{
    @XmlAttribute
    private int id;

    @XmlElement
    private Date placementDate;

    @XmlElement
    private String customerName;

    @XmlElement(name="item")
    @XmlElementWrapper(name="items")
    private List<Item> items;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPlacementDate() {
        return placementDate;
    }

    public void setPlacementDate(Date placementDate) {
        this.placementDate = placementDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public BigDecimal getOrderTotalCost() {
        BigDecimal result = new BigDecimal(0);
        if (items == null) {
            return new BigDecimal(-1);
        }
        for (Item item: items){
            result = result.add(item.getCost().multiply(item.getCount()));
        }
        return result;
    }
}
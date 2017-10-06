package com.lebo.orderApplication;

import com.lebo.orderDatabase.DatabaseManager;

import javax.xml.bind.annotation.*;
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

    public String getOrderNumStr() {
        return "Your Order Number is: " + Integer.toString(id);
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

//    public String getOrderInfo() {
//        DatabaseManager myDatabaseManager = DatabaseManager.getInstance();
//        try {
//            return myDatabaseManager.myConnection.getSchema();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return "";
//    }

//    public String getOrderHtml() {
//        DatabaseManager myDatabaseManager = DatabaseManager.getInstance();
////        return "<!DOCTYPE html><html><head><meta charset='utf - 8'><title>Orders</title></head><body><h1>" + getOrderNumStr() + "</h1></body></html>";
//        return "<!DOCTYPE html><html><head><meta charset='utf - 8'><title>Orders</title></head><body><h1>" + myDatabaseManager.getOrderInfo(id) + "</h1></body></html>";
//
//    }

}
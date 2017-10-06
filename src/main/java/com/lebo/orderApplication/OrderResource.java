package com.lebo.orderApplication;

import com.lebo.orderDatabase.DatabaseManager;
import org.jboss.resteasy.annotations.providers.jaxb.Formatted;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
//import org.glassfish.jersey.client.JerseyClientBuilder;

@Path("/")
public class OrderResource
{

    Client client = ClientBuilder.newClient();

    @GET
    @Path("/{order_id}")
    @Produces(MediaType.APPLICATION_XML)
    @Formatted
    public Order getOrder(@PathParam("order_id") int order_id) {
        DatabaseManager myDatabaseManager = DatabaseManager.getInstance();

        //Get Order Info
        String query =
                "select * " +
                "from order_api.order " +
                "where order_api.order.order_id=" + Integer.toString(order_id) + ";";

        List<Map<String,Object>> results = myDatabaseManager.queryDB(query);
        if (results.size() == 0) {
            System.out.println("Order Id does not exist");
            Order noResult = new Order();
            noResult.setId(-1);
            return noResult;
        }


        //Create Order
        Order order = new Order();
        order.setId((int) results.get(0).get("order_id"));
        order.setCustomerName((String) results.get(0).get("customer_name"));
        order.setPlacementDate((Date) results.get(0).get("placement_date"));
        order.setItems(new ArrayList<>());

        //Get Order Item Info
        query =
                "select * " +
                "from order_api.order_item " +
                "join order_api.item " +
                "on order_api.order_item.item_id=order_api.item.item_id " +
                "where order_api.order_item.order_id=" + Integer.toString(order_id) + ";";
        
        results = myDatabaseManager.queryDB(query);

        //Create Items and Add to Order
        for (Map<String,Object> line: results) {
            Item anItem = new Item();
            anItem.setId((int) line.get("item_id"));
            anItem.setCount((BigDecimal) line.get("item_count"));
            anItem.setName((String) line.get("item_name"));
            anItem.setCost((BigDecimal) line.get("item_cost"));
            order.addItem(anItem);

        }


        return order;
    }


    @GET
    @Path("/{order_id}/total-cost")
    @Produces(MediaType.APPLICATION_XML)
    @Formatted
    public TotalCost getOrderTotalCost(@PathParam("order_id") int order_id) {
        WebTarget webTarget = client.target("http://localhost:8080/order/" + Integer.toString(order_id));
        webTarget.request(MediaType.APPLICATION_XML);
        Invocation.Builder builder = webTarget.request(MediaType.APPLICATION_XML);
        Response response = builder.get();
        Order order = response.readEntity(Order.class);

        TotalCost total = new TotalCost();
        total.setAmount(order.getOrderTotalCost());
        total.setOrder_id(order_id);

        return total;
    }

}
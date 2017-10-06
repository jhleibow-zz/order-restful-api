package com.lebo.orderDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class DatabaseManager {

    private static DatabaseManager instance;

    private Connection myConnection;

    private DatabaseManager() {
        myConnection = ConnectionFactory.getMySQLConnection();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public String getOrderInfo(int orderNum) {
        Statement stmt = null;
        try {
            stmt = myConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String query = "select * from order_api.order where order_id = " + Integer.toString(orderNum);
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String resultStr = "";
        try {
            while (rs.next()) {
                resultStr += Integer.toString(rs.getInt("order_id")) + " ";
                resultStr += rs.getDate("placement_date") + " ";
                resultStr += rs.getString("customer_name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultStr;
    }


    public List<Map<String, Object>> queryDB(String query) {
        Statement stmt = null;
        try {
            stmt = myConnection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet resultSet = null;
        try {
            resultSet = stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResultSetMetaData metaData = null;
        try {
            metaData = resultSet.getMetaData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> results = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Map<String,Object> oneRow = new HashMap<>();
                for (int i = 1; i <= metaData.getColumnCount(); i++) {
                    String key = metaData.getColumnName(i);
                    switch (metaData.getColumnType(i)){
                        case Types.INTEGER: oneRow.put(key, resultSet.getInt(i));
                            break;
                        case Types.DATE: oneRow.put(key, resultSet.getDate(i));
                            break;
                        case Types.NUMERIC: oneRow.put(key, resultSet.getBigDecimal(i));
                            break;
                        default: oneRow.put(key, resultSet.getString(i));
                            break;
                    }

                }
                results.add(oneRow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }


}

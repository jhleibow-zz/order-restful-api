package com.lebo.orderDatabase;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * Class to manage database interaction.
 *
 * COPYRIGHT (C) 2017 John Leibowitz. All Rights Reserved.
 * @author John Leibowitz
 * @version 1.00
 */

public class DatabaseManager {

    private static DatabaseManager instance;

    private final Connection myConnection;

    private DatabaseManager() {
        myConnection = ConnectionFactory.getMySQLConnection();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
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
            resultSet = stmt != null ? stmt.executeQuery(query) : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ResultSetMetaData metaData = null;
        try {
            metaData = resultSet != null ? resultSet.getMetaData() : null;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<Map<String, Object>> results = new ArrayList<>();

        if (resultSet == null || metaData == null) {
            return results;
        }

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

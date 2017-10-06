package com.lebo.orderDatabase;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Helper class to get a database connection.
 *
 * COPYRIGHT (C) 2017 John Leibowitz. All Rights Reserved.
 * @author John Leibowitz
 * @version 1.00
 */

class ConnectionFactory {

    public static Connection getMySQLConnection() {
        try {
            InitialContext myContext = new InitialContext();
            DataSource myDataSource = (DataSource) myContext.lookup("java:/comp/env/jdbc/postgres");
            return myDataSource.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.lebo.orderDatabase;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getMySQLConnection() {
        try {
            InitialContext myContext = new InitialContext();
            DataSource myDataSource = (DataSource) myContext.lookup("java:/comp/env/jdbc/postgres");
            Connection myConnection = myDataSource.getConnection();
            return myConnection;
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

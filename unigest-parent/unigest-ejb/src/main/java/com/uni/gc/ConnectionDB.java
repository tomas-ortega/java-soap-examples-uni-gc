package com.uni.gc;

import java.sql.Connection;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ConnectionDB {
    
    private ConnectionDB() {
    }

    public static Connection getConnection(String contextUrl,
                                           String poolName) throws Exception {
        Connection lcnConexion = null;
        try
        {
            //Get initial context
            Context ctx = new InitialContext();
            
            //Lookap DataSource
            DataSource ds = (DataSource)
                    ctx.lookup(contextUrl + poolName);

            //Have Datasource, then get connection
            lcnConexion = ds.getConnection();

        } catch (Exception ex) {
            //throw ex;
        }
        
        return lcnConexion;
    }
    
    public static Connection getConnection(String asPoolName) throws Exception {
        return ConnectionDB.getConnection("java:jboss/datasources/", asPoolName);
    }
    
     public static void closeConnection(Connection connection) throws Exception {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch(Exception ex) {
            throw ex;
        }
    }
}

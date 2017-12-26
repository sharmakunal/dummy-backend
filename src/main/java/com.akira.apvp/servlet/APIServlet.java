package com.akira.apvp.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.google.gson.Gson;

public class APIServlet extends HttpServlet {
    DataSource pool;
    public void init() throws ServletException {
        Context env = null;
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource) env.lookup("jdbc/oracle-8i-athletes");
            if (pool == null)
                throw new ServletException(
                        "'oracle-8i-athletes' is an unknown DataSource");
        } catch (NamingException ne) {
            throw new ServletException(ne);
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = pool.getConnection();
        } catch (SQLException sqle) {
            throw new ServletException(sqle.getMessage());
        } finally {
            return conn;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String name = request.getParameter("name");
        try{
            IsExist(name);
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(new Gson().toJson("TRUE"));
        } catch (SQLException sqle){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            //throw new ServletException(sqle.getMessage( ));
        }
    }
    public void IsExist(String name) throws SQLException{
        if (name == null)
            throw new SQLException("Invalid parameter in IsExist method.");
        Connection conn = null;
        conn = getConnection( );
        if (conn == null )
            throw new SQLException("Invalid Connection in addRaceEvent method");
        CallableStatement cs = null;
        //Create an instance of the CallableStatement
        cs = conn.prepareCall("{ call name() }");
        ResultSet resultSet = cs.executeQuery();
        if(resultSet.wasNull()){
        }
        conn.close( );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

}

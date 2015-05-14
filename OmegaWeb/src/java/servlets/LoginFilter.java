/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users_connection.User;
import users_connection.UsersConnection;

/**
 *
 * @author manemarron
 */
public class LoginFilter implements Filter {

    /**
     * Constante con propósitos de depuración. Sirve para que no se tenga que
     * hacer login para realizar pruebas más rápidamente en agunos casos.
     */
    private static final boolean VALIDATION = true;

    private static final boolean debug = true;
    private static final String[] NO_LOGIN = new String[]{
        "login",
        "register",
        "login_control",
        "register_control",
        "DatabaseWS?wsdl",
        "DatabaseWS",
        "DatabaseWS?Tester"
    };

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (VALIDATION) {
            HttpSession session = req.getSession();
            if (session.getAttribute("user") == null) {
                String[] url_split = req.getRequestURI().split("/");
                if (!Arrays.asList(NO_LOGIN).contains(url_split[url_split.length - 1])) {
                    resp.sendRedirect(req.getContextPath() + "/login");
                } else {
                    chain.doFilter(request, response);
                }
            } else {
                updateSession(session);
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void updateSession(HttpSession session) {
        UsersConnection connection = new UsersConnection();
        try {
            connection.setConnection();
            String query = "SELECT * FROM USERS WHERE ID=?";
            List<?> params = Arrays.asList(new Integer[]{((User) session.getAttribute("user")).getId()});
            ResultSet rs = connection.getResultSet(query, params);
            rs.next();
            User user = User.setFromResultSet(rs);
            session.setAttribute("user", user);
        } catch (SQLException ex) {
            session.setAttribute("error", "Hubo un error. Intente más tarde");
        } finally {
            try {
                connection.closeConnection();
            } catch (SQLException ex) {
                session.setAttribute("error", "Hubo un error. Intente más tarde");
            }
        }
    }

    /**
     * Return the filter configuration object for this filter.
     *
     * @return
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            if (debug) {
                log("LoginFilter:Initializing filter");
            }
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuffer sb = new StringBuffer("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import users_connection.User;
import users_connection.UsersConnection;

/**
 *
 * @author manemarron
 */
public class SessionServlet extends HttpServlet {

    private static final String ENCRYPTION_METHOD = "SHA-1";
    private static final UsersConnection CONNECTION = new UsersConnection();

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().equals("/OmegaWeb/logout")) {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (null != request.getRequestURI()) {
                switch (request.getRequestURI()) {
                    case "/OmegaWeb/login_control":
                        out.print(login(request));
                        break;
                    case "/OmegaWeb/register_control":
                        out.print(register(request));
                        break;
                }
            }
        }
    }

    private String register(HttpServletRequest request) {
        String result = null;
        String username = request.getParameter("username");
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String pass = null;
        boolean passwords_match = false;

        try {
            String confirm_pass;
            pass = toSha1(request.getParameter("password"));
            confirm_pass = toSha1(request.getParameter("confirm_password"));
            passwords_match = Objects.equals(pass, confirm_pass);
        } catch (NoSuchAlgorithmException ex) {
            result = "{'status': 500, 'message': 'Error del servidor'}";
        }

        if (valid(username) && valid(first_name) && valid(last_name) && valid(pass) && passwords_match) {
            try {
                CONNECTION.setConnection();
                String query = String.format("INSERT INTO USERS(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME) VALUES('%s','%s','%s','%s')",
                        username, pass, first_name, last_name);
                CONNECTION.executeQuery(query);
                query = String.format("SELECT * FROM USERS WHERE username='%s'", username);
                ResultSet rs = CONNECTION.getResultSet(query);
                if (rs.next()) {
                    HttpSession session = request.getSession(true);
                    User user = new User();
                    user.setFromResultSet(rs);
                    session.setAttribute("user", user);
                    result = "{'status': 200}";
                }
            } catch (SQLException ex) {
                result = "{'status': 500, 'message': 'Error del servidor'}";
            } finally {
                try {
                    CONNECTION.closeConnection();
                } catch (SQLException ex) {
                    result = "{'status': 500, 'message': 'Error del servidor'}";
                }
            }
        }
        return result;
    }

    private String login(HttpServletRequest request) {
        String result = null;
        String username = request.getParameter("username");
        String pass = null;

        //Convertir la contraseña a sha-1 para poder comparar
        try {
            pass = toSha1(request.getParameter("password"));
        } catch (NoSuchAlgorithmException ex) {
            result = "{'status': 500, 'message': 'Error del servidor'}";
        }

        if (valid(username) && valid(pass)) {
            try {
                CONNECTION.setConnection();
                String query = String.format("SELECT * FROM USERS WHERE username='%s'", username);
                ResultSet rs = CONNECTION.getResultSet(query);
                if (rs.next()) {
                    if (pass.equals(rs.getString("password"))) {
                        User user = new User();
                        user.setFromResultSet(rs);
                        HttpSession session = request.getSession(true);
                        session.setAttribute("user", user);
                        result = "{'status': 200}";
                    } else {
                        result = "{'status': 403, 'message': 'La contraseña es inválida'}";
                    }
                } else {
                    result = "{'status': 403, 'message': 'El usuario no existe'}";
                }
            } catch (SQLException ex) {
                result = "{'status': 500, 'message': 'Error del servidor'}";
            } finally {
                try {
                    CONNECTION.closeConnection();
                } catch (SQLException ex) {
                    result = "{'status': 500, 'message': 'Error del servidor'}";
                }
            }
        }
        return result;
    }

    private String toSha1(String s) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(ENCRYPTION_METHOD);
        messageDigest.reset();
        messageDigest.update(s.getBytes());
        return new String(messageDigest.digest());
    }

    private boolean valid(String parameter) {
        return parameter != null && parameter.length() > 0;
    }

}

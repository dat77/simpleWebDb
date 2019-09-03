package com.servlets;

import com.dbaccess.Owner;
import com.dbaccess.OwnersHandler;
import com.dbaccess.OwnersHandlerMySQL;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "save", urlPatterns = {"/save"})
public class SaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ownerName = req.getParameter("ownername");
        String phoneNumber = req.getParameter("phonenumber");

        OwnersHandler handlerMySQL = OwnersHandlerMySQL.getInstance();
        try {
            handlerMySQL.addOwner(new Owner(ownerName, phoneNumber));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
        dispatcher.forward(req, resp);

    }
}

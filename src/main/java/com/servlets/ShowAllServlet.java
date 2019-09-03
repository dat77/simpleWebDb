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
import java.util.List;

@WebServlet(name = "Showall", urlPatterns = {"/showall"})
public class ShowAllServlet extends HttpServlet {

    private OwnersHandler handlerMySQL = OwnersHandlerMySQL.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Owner> owners = null;
        try {
            owners = handlerMySQL.getAllOwners();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("ownerslist", owners);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/phonelist.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Owner> owners = null;
        try {
            owners = handlerMySQL.getAllOwners();
            System.out.println(owners.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("ownerslist", owners);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/phonelist.jsp");
        dispatcher.forward(req, resp);
    }
}

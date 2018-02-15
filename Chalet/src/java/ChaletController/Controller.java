/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChaletController;

import com.sun.org.apache.xml.internal.security.utils.Base64;
import data.Repositories;
import domain.Lid;
import domain.Product;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import sun.misc.IOUtils;

/**
 *
 * @author lander
 */
@WebServlet(name = "Controller", urlPatterns = {
    "/selectUser", "/bestel", "/addMember", "/inventaris", "/addDrink", "/refill", "/settings", "/setUser"
})

public class Controller extends HttpServlet {

    // Pages:
    private static final String PAGE_LISTPRODUCTS = "WEB-INF/drankkaart.jsp";
    private static final String PAGE_USERSETTINGS = "pages/userSettings.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, UnsupportedEncodingException, SQLException {
        System.err.println("-----------" + request.getServletPath());

        switch (request.getServletPath()) {
            case "/bestel":
                bestel(request, response);
                break;

            case "/selectUser":
                getAndSetUser(request, response);
                request.getRequestDispatcher(PAGE_LISTPRODUCTS).forward(request, response);
                break;

            case "/addMember":
                addUser(request, response);
                break;

            case "/refill":
                refillProduct(request, response);
                break;

            case "/setUser":
                getAndSetUser(request, response);
                response.sendRedirect(PAGE_USERSETTINGS);
                break;

            case "/settings":
                settingUser(request, response);
                break;
                
            case "/addDrink":
                addDrink(request, response);
                break;

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
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
            throws ServletException, IOException, UnsupportedEncodingException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Lid getAndSetUser(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("user"));
        Lid l = Repositories.getLedenRepository().getMember(id);
        request.getSession().setAttribute("USER", l);
        return l;
    };
    
    private void bestel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int prodId = Integer.parseInt(request.getParameter("prod"));
        Product p = Repositories.getInventarisRepository().getProduct(prodId);

        Lid l = (Lid) request.getSession().getAttribute("USER");

        if (l != null) {
            Repositories.getInventarisRepository().updateProduct(p.id, 1);
            Repositories.getLedenRepository().updateMember(l.id, l.naam, p.prijs, p.prijs);

            request.getSession().setAttribute("USER", null);
            request.getSession().setAttribute("prod", null);
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naam = request.getParameter("naam");
        String geldAsString = request.getParameter("geld");
        double geld = Double.parseDouble(geldAsString);

        Repositories.getLedenRepository().addMember(naam, geld);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void refillProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        int prodId =  Integer.parseInt(request.getParameter("prodId"));
        int aantal = Integer.parseInt(request.getParameter("aantal"));
        
        Repositories.getInventarisRepository().refillProduct(prodId, aantal);
        response.sendRedirect(request.getContextPath() + "/pages/inventaris.jsp");
    }

    private void settingUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Lid l = (Lid) request.getSession().getAttribute("USER");

        String geldAsString = request.getParameter("geld");

        double geld = Double.parseDouble(geldAsString) * -1;
        Repositories.getLedenRepository().updateMember(l.id, l.naam, geld, 0);

        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void addDrink(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String naam = request.getParameter("naam");
        double geld = Double.parseDouble(request.getParameter("prijs"));
        int aantal = Integer.parseInt(request.getParameter("aantal"));
        System.err.println(naam +" "+geld+" "+aantal);
        
        Repositories.getInventarisRepository().addProduct(naam, geld, aantal);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

}

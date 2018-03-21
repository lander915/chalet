/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ChaletController;

import data.Repositories;
import domain.Lid;
import domain.Product;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;


/**
 *
 * @author lander
 */
@WebServlet(name = "Controller", urlPatterns = {
    "/selectUser", "/bestel", "/addMember", "/inventaris", "/addDrink", "/refill", "/settings", "/setUser", "/admin"
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
                
            case "/admin":
                checkAdmin(request, response);
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
            Repositories.getFileLog().logAction("bestel," + l.id + "," + l.naam + "," + p.prijs + "," + LocalDateTime.now());

            request.getSession().setAttribute("USER", null);
            request.getSession().setAttribute("prod", null);
        }

        request.getSession().setAttribute("ADMIN", null);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naam = request.getParameter("naam");
        String geldAsString = request.getParameter("geld");
        double geld = Double.parseDouble(geldAsString);
        String picture = request.getParameter("picture");
        
        if(picture.equals("true")){
            String imageUrl = naam+".png";
            Repositories.getLedenRepository().addMember(naam, geld, imageUrl);
        }
        else{
            Repositories.getLedenRepository().addMember(naam, geld, "default.png");
        }
        
        Repositories.getFileLog().logAction("addUser," + naam + "," + geldAsString + "," + LocalDateTime.now());
        request.getSession().setAttribute("ADMIN", null);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void refillProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, ServletException {
        int prodId =  Integer.parseInt(request.getParameter("prodId"));
        int aantal = Integer.parseInt(request.getParameter("aantal"));
        
        Repositories.getInventarisRepository().refillProduct(prodId, aantal);
        Repositories.getFileLog().logAction("refillProduct," + prodId + "," + aantal + "," + LocalDateTime.now());
        response.sendRedirect(request.getContextPath() + "/pages/inventaris.jsp");
    }

    private void settingUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Lid l = (Lid) request.getSession().getAttribute("USER");
        String picture = request.getParameter("picture");

        String geldAsString = request.getParameter("geld");
        
        if(picture.equals("true")){
            String imageUrl = l.naam+".png";
            Repositories.getLedenRepository().updatePicture(l.id, imageUrl);
        }

        double geld = Double.parseDouble(geldAsString) * -1;
        Repositories.getLedenRepository().updateMember(l.id, l.naam, geld, 0);

        Repositories.getFileLog().logAction("settingUser," + l.id + "," + l.naam + "," +geldAsString + "," + LocalDateTime.now());
        request.getSession().setAttribute("ADMIN", null);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void addDrink(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String naam = request.getParameter("naam");
        double geld = Double.parseDouble(request.getParameter("prijs"));
        int aantal = Integer.parseInt(request.getParameter("aantal"));
        System.err.println(naam +" "+geld+" "+aantal);
        String picture = request.getParameter("picture");
        
         if(picture.equals("true")){
            String imageUrl = naam+".png";
            Repositories.getInventarisRepository().addProduct(naam, geld, aantal, imageUrl);
        }
        else{
            Repositories.getInventarisRepository().addProduct(naam, geld, aantal, "default.png");
        }
        
        
        Repositories.getFileLog().logAction("addDrink," + naam + "," + geld + "," + aantal + "," + LocalDateTime.now());
        request.getSession().setAttribute("ADMIN", null);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void checkAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
        String pw = request.getParameter("pw");
        try {
            //C4780D54E2367F0B88C796692B7ED07A
            if(!DatatypeConverter.printHexBinary(MessageDigest.getInstance("MD5").digest(pw.getBytes("UTF-8"))).equals("C4780D54E2367F0B88C796692B7ED07A")){
                response.setStatus(500);
                request.getSession().setAttribute("ADMIN", null);
            }
            else{
                request.getSession().setAttribute("ADMIN", true);
            }
            
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

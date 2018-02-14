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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lander
 */
@WebServlet(name = "Controller", urlPatterns = {
    "/selectUser" , "/bestel", "/addMember", "/inventaris", "/addDrink", "/refill", "/settings"
})
public class Controller extends HttpServlet {

     // Pages:
    private static final String PAGE_LISTPRODUCTS = "WEB-INF/drankkaart.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.err.println("-----------"+request.getServletPath());
        
        switch (request.getServletPath()) {
            case "/bestel":
                bestel(request, response);
                break;

            case "/selectUser":
                setUser(request, response);
                break;
                
            case "/addMember":
                addUser(request, response);
                break;
            
            case "/refill":
                refillProduct(request, response);
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
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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

    private void bestel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int prodId = Integer.parseInt(request.getParameter("prod"));
        Product p = Repositories.getInventarisRepository().getProduct(prodId);
        
        Lid l = (Lid) request.getSession().getAttribute("USER");
        
        if (l != null) 
        {
        Repositories.getInventarisRepository().updateProduct(p.id, 1);
        Repositories.getLedenRepository().updateMember(l.id, p.prijs);
        
        request.getSession().setAttribute("USER", null);
        request.getSession().setAttribute("prod", null);
        }
        
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void setUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("user"));
        Lid l = Repositories.getLedenRepository().getMember(id);
        
        request.getSession().setAttribute("USER", l);
        
        request.getRequestDispatcher(PAGE_LISTPRODUCTS).forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String naam = request.getParameter("naam");
        String geldAsString = request.getParameter("geld");
        double geld = Double.parseDouble(geldAsString);
        
        System.err.println(naam +" "+geld);
        
        Repositories.getLedenRepository().addMember(naam, geld);
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    private void refillProduct(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

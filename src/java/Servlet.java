

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeremie
 */
public class Servlet extends HttpServlet {

    public static final String ATT_MESSAGES = "messages";
    public static final String VUE = "/WEB-INF/affichage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        Enumeration parameters = request.getParameterNames();

        ArrayList<String[]> l = new ArrayList<>(); // User
        String[] tab = new String[3]; // Data
        int n = 0;
        int ok=0;
        while (parameters.hasMoreElements()) {

            n++;
            String nomParam = (String) parameters.nextElement();
            String valeurParam = request.getParameter(nomParam);
            if(nomParam.equals("selectSearch") || nomParam.equals("inputSearch")){
                if (!request.getParameter(nomParam).equals("")) {
                    if (n == 1) {
                        tab[0] = valeurParam;
                    } else{
                        tab[1] = valeurParam;
                        l.add(tab);
                    }
                }
                ok=1;
            }else{  
                if (n == 1) {
                    tab[0] = valeurParam;
                } else if (n == 2) {
                    tab[1] = valeurParam;
                } else {
                    tab[2] = valeurParam;
                    l.add(tab);
                    tab = new String[3];
                    n = 0;
                }
            }
        }
        /* Initialisation de l'objet Java et récupération des messages */
        JDBC jdbc = new JDBC(l);
        List<User> messages = null;
        if(ok==0)
            messages = jdbc.insert(request);
        else{
            messages = jdbc.search(request);            
        }
        /* Enregistrement de la liste des messages dans l'objet requête */
        request.setAttribute(ATT_MESSAGES, messages);
        /* Transmission vers la page en charge de l'affichage des résultats */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
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
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

}

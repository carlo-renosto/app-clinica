package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import negocioImpl.NUsuarioImpl;

@WebServlet("/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");
		String paramErr = request.getParameter("err");
		
		
		if(paramValue != null && paramValue.equals("1")) {
			NUsuarioImpl usuarioImpl = new NUsuarioImpl();
	        ArrayList<Usuario> usuarios = usuarioImpl.getUsuarios();
	        
	        request.setAttribute("listaFiltro", usuarios);
			
			RequestDispatcher rd = request.getRequestDispatcher("MenuUsuario/MenuUsuario.jsp");
            rd.forward(request, response);
            return;	
		}
		
		if(paramValue != null && paramValue.equals("2")) {
			if(paramErr == null) {
				request.setAttribute("lblError", "");
			}
			else {
				request.setAttribute("lblError", paramErr);
			}
	    	
	    	RequestDispatcher rd = request.getRequestDispatcher("MenuUsuario/AnadirUsuario.jsp");
	        rd.forward(request, response);
	        return;
	    }
		
		if(paramValue != null && paramValue.equals("3")) {
			NUsuarioImpl usuarioImpl = new NUsuarioImpl();
			ArrayList<Integer> documentos = usuarioImpl.getDni();
			
			request.setAttribute("listaDni", documentos);
			
			RequestDispatcher rd = request.getRequestDispatcher("MenuUsuario/EditarUsuario.jsp");
	        rd.forward(request, response);
	        return;
		}
		
		if(paramValue != null && paramValue.equals("4")) {
			NUsuarioImpl usuarioImpl = new NUsuarioImpl();
			ArrayList<Integer> documentos = usuarioImpl.getDni();
			
			request.setAttribute("listaDni", documentos);
			
			RequestDispatcher rd = request.getRequestDispatcher("MenuUsuario/BorrarUsuario.jsp");
	        rd.forward(request, response);
	        return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    doGet(request, response);
	}
}

package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocioImpl.NUsuarioImpl;

@WebServlet("/BorrarUsuarioServlet")
public class BorrarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BorrarUsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("ddlDni");
		
		if(dni != null && !dni.isEmpty()) {
			NUsuarioImpl usuarioImpl = new NUsuarioImpl();
			usuarioImpl.eliminarUsuario(Integer.parseInt(dni));
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("UsuarioServlet?Param=4");
        rd.forward(request, response);
	}
}

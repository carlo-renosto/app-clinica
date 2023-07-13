package servlets;

import java.util.List;
import java.util.ListIterator;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import entidad.Usuario;
import negocioImpl.NUsuarioImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public LoginServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String dni = request.getParameter("txtDni");
		String contra = request.getParameter("txtContra");
		
		int dniValue;
		
		try {
			dniValue = Integer.parseInt(dni);
		}
		catch(NumberFormatException e) {
			request.setAttribute("lblError", "Introducir DNI numerico.");

			RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
			rd.forward(request, response);
			return; 
		}

		NUsuarioImpl usuarioImpl = new NUsuarioImpl();
		List<Usuario> usuarios = usuarioImpl.getUsuarios();

		ListIterator<Usuario> it = usuarios.listIterator();
		while (it.hasNext()) {
			Usuario userAux = it.next();
			if (dniValue == userAux.getDni() && contra.equals(userAux.getContra())) {
				request.setAttribute("lblError", "");

				HttpSession session = request.getSession();
				session.setAttribute("dni", dni);
				session.setAttribute("nombre", userAux.getNombre());

				if (userAux.isEsAdmin()) {
					RequestDispatcher rd = request.getRequestDispatcher("/Inicio.jsp");
					rd.forward(request, response);
					return; 
				} else {
					RequestDispatcher rd = request.getRequestDispatcher("/InicioMedico.jsp");
					rd.forward(request, response);
					return;
				}
			}
		}
		request.setAttribute("lblError", "Credenciales incorrectas.");

		RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
		rd.forward(request, response);
		return; 
	}
}

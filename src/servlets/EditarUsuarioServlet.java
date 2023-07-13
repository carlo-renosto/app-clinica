package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import exceptions.UsuarioException;
import negocioImpl.NUsuarioImpl;

@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EditarUsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramValue = request.getParameter("Param");
		
		if(paramValue != null && paramValue.equals("1")) {
			int dni = Integer.parseInt(request.getParameter("ddlDni"));
			
			NUsuarioImpl usuarioImpl = new NUsuarioImpl();
			Usuario usuario = usuarioImpl.getUsuarioSelect(dni);
			
			request.setAttribute("usuario", usuario);
			
			RequestDispatcher rd = request.getRequestDispatcher("UsuarioServlet?Param=3");
	        rd.forward(request, response);
	        return;
		}
		
		if(paramValue != null && paramValue.equals("2")) {
			boolean aux = false;
			
			try {
				if(request.getParameter("btnEditar") != null) {
					int id = Integer.parseInt(request.getParameter("txtId"));
					int dni = Integer.parseInt(request.getParameter("txtDni"));
					String nombre = request.getParameter("txtUsuario");
					String contra = request.getParameter("txtContrasena");
					boolean esAdmin = (request.getParameter("ddlTipo").equals("Admin")) ? true : false;
					
					Usuario usuario = new Usuario(id, dni, nombre, contra, esAdmin);
					
					NUsuarioImpl usuarioImpl = new NUsuarioImpl();
					if(usuarioImpl.editarUsuario(usuario) > 0) {
						aux = true;
					}
					else {
						throw new UsuarioException("Edicion del usuario fallida");
					}
				}
				else {
					throw new UsuarioException("Formulario no enviado correctamente");
				}
			}
			catch(UsuarioException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("edito", aux);
			
			RequestDispatcher rd = request.getRequestDispatcher("UsuarioServlet?Param=3");
	        rd.forward(request, response);
		}
	}
}

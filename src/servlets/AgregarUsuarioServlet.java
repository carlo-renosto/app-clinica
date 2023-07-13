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

@WebServlet("/AgregarUsuarioServlet")
public class AgregarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AgregarUsuarioServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String dni = request.getParameter("txtDni");
		String nombre = request.getParameter("txtUsuario");
		String tipo = request.getParameter("ddlTipo");
		String contra = request.getParameter("txtContrasena");
		String contra2 = request.getParameter("txtContrasena2");

		NUsuarioImpl usuarioImpl = new NUsuarioImpl();
		
		String err = "";
		try {
			if((nombre == null || nombre.isEmpty()) || (contra == null || contra.isEmpty()) || (contra2 == null || contra2.isEmpty())) {
				err = "Completar todos los campos.";

				throw new UsuarioException("Campos incompletos");
			} 
			else if(!contra.equals(contra2)) {
				err = "Contraseñas no coinciden.";

				throw new UsuarioException("Contraseñas no coinciden");
			} 
			else if(usuarioImpl.existeDni(Integer.parseInt(dni))) {
				err = "DNI no disponible. (En uso)";

				throw new UsuarioException("DNI no disponible (En uso)");
			} 
			else if(usuarioImpl.existeUsuario(nombre)) {
				err = "Usuario no disponible. (En uso)";

				throw new UsuarioException("Usuario no disponible (En uso)");
			} 
			else {
				err = "Usuario registrado exitosamente.";

				Usuario usuario = new Usuario(0, Integer.parseInt(dni), nombre, contra, true);

				if(tipo.equals("Medico"))
					usuario.setEsAdmin(false);

				usuarioImpl.agregarUsuario(usuario);
			}
		} 
		catch (UsuarioException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = request.getRequestDispatcher("UsuarioServlet?Param=2&err=" + err);
		rd.forward(request, response);
	}
}

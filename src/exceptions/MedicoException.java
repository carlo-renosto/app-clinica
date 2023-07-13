package exceptions;

public class MedicoException extends Exception {
	private static final long serialVersionUID = 1L;

	public MedicoException(String mensaje) {
        super(mensaje);
    }
}
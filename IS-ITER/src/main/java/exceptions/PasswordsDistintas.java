package exceptions;

public class PasswordsDistintas extends Exception {
	private static final long serialVersionUID = 1L;
	public PasswordsDistintas() {
		super();
	}
	public PasswordsDistintas(String s) {
		super(s);
	}
}

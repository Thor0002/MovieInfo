package application.exception;

/**
 * Исключения, которое выбрасывается если запрос некорректный.
 */
public class IllegalRequestException extends RuntimeException {

	private static final long serialVersionUID = 8254367825170671511L;

	public IllegalRequestException(String message) {
		super(message);
	}

}

package application.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = -8931248185489209304L;

	private final int status;
	private final String message;
	private String stackTrace;
	private List<ValidationError> errors;

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public List<ValidationError> getErrors() {
		return errors;
	}

	public void setErrors(List<ValidationError> errors) {
		this.errors = errors;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public ErrorResponse(int status, String message) {
		super();
		this.status = status;
		this.message = message;
	}



	private static class ValidationError implements Serializable {
		private static final long serialVersionUID = 37346972888242309L;

		private final String field;
		private final String message;

		public ValidationError(String field, String message) {
			this.field = field;
			this.message = message;
		}

		public String getField() {
			return field;
		}

		public String getMessage() {
			return message;
		}

	}

	public void addValidationError(String field, String message) {
		if (Objects.isNull(errors)) {
			errors = new ArrayList<>();
		}
		errors.add(new ValidationError(field, message));
	}
}

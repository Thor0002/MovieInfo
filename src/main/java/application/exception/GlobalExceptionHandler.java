package application.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(IllegalRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity handleIllegalRequestException(IllegalRequestException exception, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		return buildErrorResponse(exception, HttpStatus.BAD_REQUEST, request);
	}

	private ResponseEntity buildErrorResponse(Exception exception, HttpStatus httpStatus, WebRequest request) {
		logger.error(exception.getMessage(), exception);
		return buildErrorResponse(exception, exception.getMessage(), httpStatus, request);
	}

	private ResponseEntity buildErrorResponse(Exception exception, String message, HttpStatus httpStatus,
			WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
		errorResponse.setStackTrace(exception.getStackTrace().toString());
		return ResponseEntity.status(httpStatus).body(errorResponse);
	}

	@Override
	public ResponseEntity handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		return buildErrorResponse(ex, status, request);
	}

}

package com.centene.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class CenteneControllerAdvice extends ResponseEntityExceptionHandler {
	
	protected static final Logger log = LogManager.getLogger(CenteneControllerAdvice.class);

	/**
	 * 
	 * @param request
	 * @param ex
	 * @return
	 */
	@ExceptionHandler(CenteneException.class)
	@ResponseBody
	public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		return new ResponseEntity<>(
										new CustomGenericException(status.value(), ex.getMessage())
									    , status
								);
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleException(Exception ex, WebRequest request,	HttpServletResponse response) {
		log.info("Just Exception");
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement ste : ex.getStackTrace()) {
			sb.append(ste.toString());
			sb.append("\n");
		}
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), sb.toString(), request.getDescription(true), HttpStatus.INTERNAL_SERVER_ERROR.value(), "INTERNAL_SERVER_ERROR");

		ex.printStackTrace();

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @param response
	 * @return
	 */
	@ExceptionHandler(HttpClientErrorException.class)
	public final ResponseEntity<ErrorDetails> handleException(HttpClientErrorException ex, WebRequest request,
			HttpServletResponse response) {
		log.info("HttpClientErrorException");
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement ste : ex.getStackTrace()) {
			sb.append(ste.toString());
			sb.append("\n");
		}
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), sb.toString(), request.getDescription(true), ex.getRawStatusCode(), ex.getLocalizedMessage());

		ex.printStackTrace();

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @param response
	 * @return
	 */
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<ErrorDetails> handleNullPointerException(NullPointerException ex, WebRequest request,
			HttpServletResponse response) {
		log.info("NullPointerException");
		StringBuilder sb = new StringBuilder();
		for (StackTraceElement ste : ex.getStackTrace()) {
			sb.append(ste.toString());
			sb.append("\n");
		}
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), sb.toString(), request.getDescription(true),
				HttpStatus.INTERNAL_SERVER_ERROR.value());

		ex.printStackTrace();

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */

	@ExceptionHandler(HTTPException.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(HTTPException ex, WebRequest request) {
		log.info("HTTPException");
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
				request.getDescription(true));
		ex.printStackTrace();

		return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * 
	 * @param e
	 * @param redirectAttributes
	 * @return
	 */
	@ExceptionHandler(MultipartException.class)
	public String handleError1(MultipartException e, RedirectAttributes redirectAttributes) {
		log.info("MultipartException");
		redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());

		e.printStackTrace();

		return "redirect:/upload-status";

	}
}

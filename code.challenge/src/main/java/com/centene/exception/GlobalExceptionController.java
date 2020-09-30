package com.centene.exception;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 
 * @author Larry T. Phan
 *
 */
@ControllerAdvice
public class GlobalExceptionController {

	private Logger log = LoggerFactory.getLogger(GlobalExceptionController.class);

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

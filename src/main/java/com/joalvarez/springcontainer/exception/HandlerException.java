package com.joalvarez.springcontainer.exception;

import com.joalvarez.springcontainer.constants.ErrorCode;
import com.joalvarez.springcontainer.data.dto.generals.HttpDTO;
import com.joalvarez.springcontainer.data.dto.generals.ResponseDTO;
import com.joalvarez.springcontainer.shared.HasLogger;
import com.joalvarez.springcontainer.exception.generals.HttpErrorException;
import com.joalvarez.springcontainer.exception.generals.GenericException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException implements HasLogger {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseDTO> handleValidationExceptions(MethodArgumentNotValidException e) {
		return ResponseEntity.badRequest().body(new ResponseDTO(1, "", e.getBindingResult().getAllErrors().stream()
			.filter(error -> error instanceof FieldError)
			.map(error -> (FieldError) error)
			.map(
				(error) ->
					String.format(
						"The field {%s} is invalid for the value {%s} with the following cause: %s",
						error.getField(),
						error.getRejectedValue(),
						error.getDefaultMessage()))
			.collect(Collectors.toList())));
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseDTO> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
		return ResponseEntity.badRequest().body(new ResponseDTO(ErrorCode.ATTRIBUTE_VALIDATION.code(), String.format(
			"The parameter {{%s}} has an invalid value of {{%s}} for the desired type {{%s}}",
			ex.getName(),
			ex.getValue(),
			ex.getRequiredType() != null
				? ex.getRequiredType().getSimpleName()
				: "Unknown"), List.of()));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ResponseDTO> handleMissingRequestParam(MissingServletRequestParameterException e) {
		return ResponseEntity
			.badRequest()
			.body(new ResponseDTO(ErrorCode.ATTRIBUTE_VALIDATION.code(), e.getMessage(), List.of()));
	}

	@ExceptionHandler(HttpErrorException.class)
	public ResponseEntity<HttpDTO> handle(HttpErrorException e) {
		this.warn(
			"Nested error occurred with the following cause: {} ",
			String.valueOf(e.getNested()),
			e);
		return new ResponseEntity<>(new HttpDTO(e.getResponseCode(), e.getMessage(), List.of(), e.getNested()), e.getCode());
	}

	@ExceptionHandler(GenericException.class)
	public ResponseEntity<ResponseDTO> handle(GenericException e) {
		this.warn(e.getMessage());

		return ResponseEntity.badRequest().body(new ResponseDTO(e.getResponseCode(), e.getMessage(), List.of()));
	}
}

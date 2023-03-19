package com.airline.customer.exceptions.miles;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import com.airline.customer.controller.MilesController;
import com.airline.customer.exceptions.ErrorResponseMessage;

@ResponseBody
@ControllerAdvice(basePackageClasses = MilesController.class)
public class MilesControllerExceptionHandler {

  @ExceptionHandler(MilesNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponseMessage resourceNotFoundException(MilesNotFoundException ex, WebRequest request) {
    ErrorResponseMessage message = new ErrorResponseMessage(
        HttpStatus.NOT_FOUND,
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return message;
  }
}

package com.optimagrowth.license.controller;

import com.optimagrowth.license.model.utils.ErrorMessage;
import com.optimagrowth.license.model.utils.ResponseWrapper;
import com.optimagrowth.license.model.utils.RestErrorList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static java.util.Collections.singletonMap;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController extends ResponseEntityExceptionHandler {
    public @ResponseBody ResponseEntity<ResponseWrapper> handleException(HttpServletRequest request, ResponseWrapper wrapper) {
        return ResponseEntity.ok(wrapper);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseWrapper> handleIOException (HttpServletRequest request, RuntimeException runtimeException) {
        RestErrorList errorMessages = new RestErrorList(HttpStatus.NOT_ACCEPTABLE, new ErrorMessage(runtimeException.getMessage(), runtimeException.getMessage()));
        ResponseWrapper responseWrapper = new ResponseWrapper(null, singletonMap("status", HttpStatus.NOT_ACCEPTABLE), errorMessages);

        return ResponseEntity.ok(responseWrapper);
    }
}

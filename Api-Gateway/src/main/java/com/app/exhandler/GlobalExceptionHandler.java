package com.app.exhandler;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;





@ControllerAdvice
public class GlobalExceptionHandler
{

	 
	 //for all runtime exceptions
	 @ExceptionHandler(Throwable.class)
		public ResponseEntity<?> handleRuntimeException(Throwable e) {
			System.out.println("in throwable exc handler");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(e.getMessage()));
		}
		
  }

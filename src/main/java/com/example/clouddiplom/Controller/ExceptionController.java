package com.example.clouddiplom.Controller;

import com.example.clouddiplom.Exaption.ExceptionRS;
import com.example.clouddiplom.Exaption.InputDataException;
import com.example.clouddiplom.Exaption.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<ExceptionRS> handleInputData(InputDataException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionRS(e.getMessage(), 400));
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionRS> handleUnauthorized(UnauthorizedException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ExceptionRS(e.getMessage(), 401));
    }
}

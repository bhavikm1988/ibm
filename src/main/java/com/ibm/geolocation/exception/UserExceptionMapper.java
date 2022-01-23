package com.ibm.geolocation.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserExceptionMapper implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        if( exception instanceof ConstraintViolationException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(prepareMessage((ConstraintViolationException)exception))
                    .type("application/json")
                    .build();
        } else if( exception instanceof UnknownHostException) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(prepareMessage((UnknownHostException)exception))
                    .type("application/json")
                    .build();
        } else if( exception instanceof NotFoundException) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(prepareMessage((NotFoundException)exception))
                    .type("application/json")
                    .build();
        }
        else return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(prepareMessage(exception))
                    .type("application/json")
                    .build();
    }

    private List<ErrorMessage> prepareMessage(ConstraintViolationException exception) {
        List<ErrorMessage> errors = new ArrayList<>();
        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
            ErrorMessage err = new ErrorMessage();
            err.setStatus(HttpStatus.BAD_REQUEST.toString());
            err.setMessage(cv.getMessage());
            errors.add(err);
        }
        return errors;
    }

    private ErrorMessage prepareMessage(UnknownHostException exception) {
        ErrorMessage err = new ErrorMessage();
        err.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.toString());
        err.setMessage(exception.getMessage());
        return err;
    }

    private ErrorMessage prepareMessage(NotFoundException exception) {
        ErrorMessage err = new ErrorMessage();
        err.setStatus(HttpStatus.NOT_FOUND.toString());
        err.setMessage(exception.getMessage());
        return err;
    }


    private ErrorMessage prepareMessage(Exception exception) {
        ErrorMessage err = new ErrorMessage();
        err.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        err.setMessage(exception.getMessage());
        return err;
    }

}
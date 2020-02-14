package com.genting.moneyexchanger;

import com.genting.moneyexchanger.exchange.TransactionsNotFoundException;
import com.genting.moneyexchanger.rate.CurrencyRatesNotFoundException;
import com.genting.moneyexchanger.util.ErrorMessagesEnum;
import com.genting.moneyexchanger.util.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * <p>This class handles all the exceptions in REST services</p>
 */
@RestController
@RestControllerAdvice
public class LocalResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleApplicationExceptions(Exception ex,
                                                                    WebRequest request) {
        return buildResponseEntity(ErrorMessagesEnum.INTERNAL_SERVER_ERROR.getErrorCode(),
                request.getDescription(false),
                ErrorMessagesEnum.INTERNAL_SERVER_ERROR.getErrorMessage(), new Date(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CurrencyRatesNotFoundException.class)
    public final ResponseEntity<Object> handleRatesNotFoundException(CurrencyRatesNotFoundException ex,
                                                                    WebRequest request) {
        return buildResponseEntity(ErrorMessagesEnum.CURRENCY_RATES_NOT_FOUND.getErrorCode(),
                request.getDescription(false),
                ErrorMessagesEnum.CURRENCY_RATES_NOT_FOUND.getErrorMessage(), new Date(),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransactionsNotFoundException.class)
    public final ResponseEntity<Object> handleTransactionsNotFoundException(TransactionsNotFoundException ex,
                                                                            WebRequest request) {
        return buildResponseEntity(ErrorMessagesEnum.TRANSACTION_NOT_FOUND.getErrorCode(),
                request.getDescription(false),
                ErrorMessagesEnum.TRANSACTION_NOT_FOUND.getErrorMessage(), new Date(),
                HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<Object> buildResponseEntity(int errorCode, String description, String message, Date dateTime, HttpStatus httpStatus) {
        ResponseEntityBuilder responseEntityBuilder = new ResponseEntityBuilder();
        return responseEntityBuilder.code(errorCode)
                .description(description)
                .message(message)
                .dateTime(dateTime)
                .httpStatus(httpStatus)
                .build();
    }

    private class ResponseEntityBuilder {
        private ErrorResponse errorResponse;
        private HttpStatus httpStatus;

        public ResponseEntityBuilder() {
            errorResponse = new ErrorResponse();
        }

        public ResponseEntityBuilder code(int code) {
            errorResponse.setCode(code);
            return this;
        }

        public ResponseEntityBuilder message(String message) {
            errorResponse.setMessage(message);
            return this;
        }

        public ResponseEntityBuilder description(String description) {
            errorResponse.setDescription(description);
            return this;
        }

        public ResponseEntityBuilder dateTime(Date dateTime) {
            errorResponse.setDateTime(dateTime);
            return this;
        }

        public ResponseEntityBuilder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public ResponseEntity<Object> build() {
            return new ResponseEntity<>(errorResponse, this.httpStatus);
        }
    }
}

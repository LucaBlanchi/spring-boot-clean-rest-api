package it.lucablanchi.itemsspringapi.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}

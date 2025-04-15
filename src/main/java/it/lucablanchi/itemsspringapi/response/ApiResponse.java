package it.lucablanchi.itemsspringapi.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiResponse<T>(
        LocalDateTime timestamp,
        int status,
        String message,
        T data,
        Map<String, String> errors
) {
    public static <T> ApiResponse<T> success(int status, String message, T data) {
        return new ApiResponse<>(LocalDateTime.now(), status, message, data, null);
    }

    public static ApiResponse<?> error(int status, String message, Map<String, String> errors) {
        return new ApiResponse<>(LocalDateTime.now(), status, message, null, errors);
    }
}

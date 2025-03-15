package com.example.prm392.dto.response;


import com.example.prm392.web.error.ApiError;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> {
    ApiError status;
    private T data;

    public static <T> Response<T> ok(T data) {
        final ApiError stats = new ApiError(HttpStatus.OK.value());
        return Response.<T>builder()
                .data(data)
                .status(stats)
                .build();
    }

    public static <T> Response<T> created(T data) {
        final ApiError stats = new ApiError(HttpStatus.CREATED.value());
        return Response.<T>builder()
                .data(data)
                .status(stats)
                .build();
    }

    public static <T> Response<T> accept(T data) {
        final ApiError stats = new ApiError(HttpStatus.ACCEPTED.value());
        return Response.<T>builder()
                .data(data)
                .status(stats)
                .build();
    }

    public static <T> Response<T> fail(String message, int code) {
        final ApiError status = new ApiError(message, code);
        return Response.<T>builder()
                .status(status)
                .build();

    }

    public static <T> Response<T> fail(ApiError status) {
        return Response.<T>builder()
                .status(status)
                .build();
    }

    public static  <T>Response<T> noContent(){
        ApiError status = new ApiError(HttpStatus.NO_CONTENT.value());
        return Response.<T>builder()
                .status(status)
                .build();
    }
}

package com.example.todo.DTO;

public class ApiResponse<T> {

    private String message;
    private T result;
    private T results;

    public ApiResponse(String message, T results, T result) {
        this.message = message;
        this.results = results;
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public T getResults() {
        return results;
    }

    public T getResult() {
        return result;
    }
}

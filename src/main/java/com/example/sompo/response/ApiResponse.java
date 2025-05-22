package com.example.sompo.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiResponse<T> {
    @JsonProperty("data")
    private T data;
    private Meta meta;

    public ApiResponse(T data, int status, String message) {
        this.data = data;
        this.meta = new Meta(status, message);
    }

    public static class Meta {
        private int status;
        private String message;

        public Meta(int status, String message) {
            this.status = status;
            this.message = message;
        }

        // Getters and setters
        public int getStatus() { return status; }
        public String getMessage() { return message; }

        public void setStatus(int status) { this.status = status; }
        public void setMessage(String message) { this.message = message; }
    }

    // Getters and setters
    public T getData() { return data; }
    public Meta getMeta() { return meta; }

    public void setData(T data) { this.data = data; }
    public void setMeta(Meta meta) { this.meta = meta; }
}


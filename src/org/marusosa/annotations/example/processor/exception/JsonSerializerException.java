package org.marusosa.annotations.example.processor.exception;

public class JsonSerializerException extends RuntimeException{
    public JsonSerializerException(String message) {
        super(message);
    }
}

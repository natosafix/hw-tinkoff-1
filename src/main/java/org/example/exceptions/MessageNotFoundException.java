package org.example.exceptions;

public class MessageNotFoundException extends RuntimeException {
    public int id;

    public MessageNotFoundException(int id) {
        this.id = id;
    }
}

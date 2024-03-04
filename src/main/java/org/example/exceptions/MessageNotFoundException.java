package org.example.exceptions;

public class MessageNotFoundException extends BaseNotFoundException {
    public MessageNotFoundException(int id) {
        super("Не найдено сообщение с id=" + id);
    }
}

package org.example.exceptions;

public class ImageNotFoundException extends BaseNotFoundException {
    public ImageNotFoundException(String link) {
        super("Не найдена картинка с link=" + link);
    }
}

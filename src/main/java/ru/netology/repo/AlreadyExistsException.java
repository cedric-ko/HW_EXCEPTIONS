package ru.netology.repo;

public class AlreadyExistsException extends RuntimeException {

    public AlreadyExistsException(String s) {
        super(s);
    }
}

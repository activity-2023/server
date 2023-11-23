package fr.cyu.depinfo.activity.model;

import java.io.Serial;

public class BadDataException extends Exception {
    @Serial
    private static final long serialVersionUID = -3002123700208285349L;

    public BadDataException(String message) {
        super(message);
    }
}

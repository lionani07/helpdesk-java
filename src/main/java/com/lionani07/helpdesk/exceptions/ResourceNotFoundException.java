package com.lionani07.helpdesk.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final String MSG = "%s not found for id=%s";

    public ResourceNotFoundException(final Class<?> clazz, final Integer id) {
        super(String.format(MSG, clazz.getSimpleName(), id));
    }
}

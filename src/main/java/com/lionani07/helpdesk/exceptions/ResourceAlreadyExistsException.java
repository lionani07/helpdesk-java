package com.lionani07.helpdesk.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {

    private static final String MSG = "%s already exists by field=%s, value=%s";
    public ResourceAlreadyExistsException(final Class<?> clazz, final String field, final String value) {
        super(String.format(MSG, clazz.getSimpleName(), field, value));
    }
}

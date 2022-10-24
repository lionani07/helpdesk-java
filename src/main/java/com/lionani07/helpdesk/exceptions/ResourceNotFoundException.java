package com.lionani07.helpdesk.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final String MSG = "Resource %s not found for id=%s";

    public ResourceNotFoundException(Class<?> clazz, Integer id) {
        super(String.format(MSG, clazz.getName(), id));
    }
}

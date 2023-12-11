package com.Hos.core.common.exception;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;

public interface ExceptionResolver {
    /**
     * This method is used to construct error resolver
     *
     * @param statusCode - status code of error
     * @param msg        - error trace
     * @return ErrorMessage - error message builder
     */
    ErrorMessage resolveError(final HttpStatus statusCode, final String msg);
}


package com.app.exception;

/**
 * Class for ResourceNotFoundException extending {@link RuntimeException}
 * used in {@link ApiExceptionHandler}
 * @author Kamlesh
 *
 */
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5861310537366287163L;

    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(final String message) {
        super(message);
    }

    public ResourceNotFoundException(final Throwable cause) {
        super(cause);
    }
}
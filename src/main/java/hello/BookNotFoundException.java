package hello;

class BookNotFoundException extends RuntimeException {

    BookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    // ...
}

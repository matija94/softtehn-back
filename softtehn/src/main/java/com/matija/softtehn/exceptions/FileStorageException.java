package com.matija.softtehn.exceptions;

import java.io.IOException;

public class FileStorageException extends RuntimeException {

    public FileStorageException(String description) {
        super(description);
    }

    public FileStorageException(String s, IOException ex) {
        super(s, ex);
    }
}

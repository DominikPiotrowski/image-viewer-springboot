package com.dominikpiotrowski.imageviewerspringboot.cloudinary;

public class PhotoUploadException extends RuntimeException{

    protected PhotoUploadException(String message) {
        super(message);
    }

}

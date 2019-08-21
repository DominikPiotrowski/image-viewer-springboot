package com.dominikpiotrowski.imageviewerspringboot.cloudinary;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dominikpiotrowski.imageviewerspringboot.models.Photo;
import com.dominikpiotrowski.imageviewerspringboot.repos.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class PhotoHandling {
    private Cloudinary cloudinary;
    private PhotoRepository photoRepository;

    @Autowired
    public PhotoHandling(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "x",
                "api_key", "x",
                "api_secret", "x"
        ));
    }

    public String uploadAndSaveToRepo(String path) {
        File file = new File(path);
        Map uploadResult;

        try {
            uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            photoRepository.save(new Photo(uploadResult.get("url").toString()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new PhotoUploadException("File couldn't be uploaded.");
        }
        return "";
    }
}
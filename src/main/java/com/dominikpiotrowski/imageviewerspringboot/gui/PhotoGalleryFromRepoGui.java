package com.dominikpiotrowski.imageviewerspringboot.gui;

import com.dominikpiotrowski.imageviewerspringboot.models.Photo;
import com.dominikpiotrowski.imageviewerspringboot.repos.PhotoRepository;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Route("gallery")
public class PhotoGalleryFromRepoGui extends VerticalLayout {

    private PhotoRepository photoRepository;

    @Autowired
    public PhotoGalleryFromRepoGui(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;

        List<Photo> photoList = photoRepository.findAll();

        photoList
                .stream()
                .forEach(pic -> {
                    Image image = new Image(pic.getPhotoAddress(), "no photo avaliable");
                    image.setWidth("300");
                    image.setHeight("300");
                    add(image);
                });
    }

}

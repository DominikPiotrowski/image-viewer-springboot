package com.dominikpiotrowski.imageviewerspringboot.gui;

import com.dominikpiotrowski.imageviewerspringboot.cloudinary.PhotoHandling;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("upload")
public class PhotoUploadGui extends VerticalLayout {

    private PhotoHandling imageUpload;

    @Autowired
    public PhotoUploadGui(PhotoHandling imageUpload) {
        this.imageUpload = imageUpload;

        H3 header = new H3("Welcome to picture upload.");
        H4 success = new H4();
        TextField url = new TextField("Please enter url:");
        url.setWidth("40%");
        Button submit = new Button("Submit");

        submit.addClickListener(clickEvent ->
        {
            String uploadedPic = imageUpload.uploadAndSaveToRepo(url.getValue());
            success.setText("Upload complete.");
            add(success);
        });

        setAlignItems(Alignment.CENTER);
        url.focus();
        add(header, url, submit);
    }
}
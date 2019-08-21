package com.dominikpiotrowski.imageviewerspringboot.repos;

import com.dominikpiotrowski.imageviewerspringboot.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long>  {
}

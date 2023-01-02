package com.example.pathdemo.repository;

import com.example.pathdemo.model.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {
    @Query("select p.url from Picture p")
    List<String> findAllPictureUrl();
}

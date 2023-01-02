package com.example.pathdemo.sevice.impl;

import com.example.pathdemo.repository.PictureRepository;
import com.example.pathdemo.sevice.PictureService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<String> findAllPictures() {
        return pictureRepository.findAllPictureUrl();
    }
}

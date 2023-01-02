package com.example.pathdemo.sevice.impl;

import com.example.pathdemo.model.entity.User;
import com.example.pathdemo.model.entity.enums.LevelEnum;
import com.example.pathdemo.model.service.UserServiceModel;
import com.example.pathdemo.repository.UserRepository;
import com.example.pathdemo.sevice.UserService;
import com.example.pathdemo.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
        private final UserRepository userRepository;
        private final ModelMapper modelMapper;
        private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void register(UserServiceModel userServiceModel) {
        User user =modelMapper.map(userServiceModel,User.class);
        user.setLevel(LevelEnum.BEGINNER);

        userRepository.save(user);

    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password)
                .map(user -> modelMapper.map(user,UserServiceModel.class))
                .orElse(null);

    }

    @Override
    public void userLogin(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }

    @Override
    public void logout() {
        currentUser.setUsername(null);
        currentUser.setId(null);

    }

    @Override
    public UserServiceModel findById(Long id) {
        return userRepository.findById(id).
                map(user -> modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }

    @Override
    public boolean usernameExist(String username) {

        return userRepository.existsByUsername(username);
    }
}

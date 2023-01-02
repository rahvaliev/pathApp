package com.example.pathdemo.sevice;

import com.example.pathdemo.model.entity.User;
import com.example.pathdemo.model.service.UserServiceModel;

public interface UserService {
    void register(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void userLogin(Long id, String username);

    void logout();

    UserServiceModel findById(Long id);

    boolean usernameExist(String username);
}

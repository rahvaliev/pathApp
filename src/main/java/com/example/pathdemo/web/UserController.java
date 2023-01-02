package com.example.pathdemo.web;

import com.example.pathdemo.model.binding.UserLoginBindingModel;
import com.example.pathdemo.model.binding.UserRegisterBindingModel;
import com.example.pathdemo.model.service.UserServiceModel;
import com.example.pathdemo.model.view.UserViewModel;
import com.example.pathdemo.sevice.UserService;
import com.example.pathdemo.util.CurrentUser;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserController(UserService userService, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel(){
        return new UserRegisterBindingModel();
    }
    @ModelAttribute
    public UserLoginBindingModel userLoginBindingModel(){
        return new UserLoginBindingModel();
    }

    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @PostMapping("/register")
    public String confirmRegister(@Valid UserRegisterBindingModel userRegisterBindingModel,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes){
        if(bindingResult.hasErrors() || !userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())){
            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
            return  "redirect:register";
        }

        boolean isUsernameExist=userService.usernameExist(userRegisterBindingModel.getUsername());
        if(isUsernameExist){

            redirectAttributes.addFlashAttribute("userRegisterBindingModel",userRegisterBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel",
                    bindingResult);
            //TODO message wrong duplicate username
            return "redirect:register";
        }

        userService.register(modelMapper.map(userRegisterBindingModel, UserServiceModel.class));


        return "redirect:login";
    }

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("notExists",false);

        return "login";
    }

    @PostMapping("/login")
    public String confirmLogin(@Valid UserLoginBindingModel userLoginBindingModel,
                               BindingResult bindingResult,RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("userLoginBindingModel",userLoginBindingModel)
                .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                        bindingResult);
        UserServiceModel user=userService.findByUsernameAndPassword(userLoginBindingModel.getUsername(),
                userLoginBindingModel.getPassword());

        if(user==null){
            redirectAttributes
                    .addFlashAttribute("notExists",true)
                    .addFlashAttribute("userLoginBindingModel",userLoginBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userLoginBindingModel",
                            bindingResult);
            return "redirect:login";
        }

       userService.userLogin(user.getId(),user.getUsername());

        return "redirect:/";
    }


    @GetMapping("/logout")
    public String logout(){
        userService.logout();


        return "redirect:/";

    }

    @GetMapping("/profile/{id}")
    public String profile(@PathVariable Long id,Model model){

        model.addAttribute("user",modelMapper.map(
                userService.findById(id), UserViewModel.class));

        return "profile";
    }


}

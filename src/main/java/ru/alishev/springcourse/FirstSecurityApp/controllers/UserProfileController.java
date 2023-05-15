package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;

@Controller
@RequestMapping("/userprofile")
public class UserProfileController {
    private final UserProfileDAO userProfileDAO;


    @Autowired

    public UserProfileController(UserProfileDAO userProfileDAO, PasswordEncoder passwordEncoder) {
        this.userProfileDAO = userProfileDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userProfileDAO.getAllUsers());
        for(int i=0; i<userProfileDAO.getAllUsers().size();i++){
            System.out.println(userProfileDAO.getAllUsers().get(i).getStudentId());
        }
        System.out.println("index");
        return "userprofile/index";
    }
    //
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, Model model) {
//        model.addAttribute("userprofile", userProfileDAO.getUserById(id));
//        return "userprofile/show";
//    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        System.out.println("omad");
        model.addAttribute("userprofile", userProfileDAO.getUserById(id));
        System.out.println("get-edit");
        return "userprofile/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("userprofile") UserProfile userProfile, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        System.out.println("user-edit");
        if (bindingResult.hasErrors())
            return "userprofile/edit";

        userProfileDAO.updateUser(id, userProfile);
        return "redirect:/posts";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userProfileDAO.deleteUser(id);
        return "redirect:/userprofile";
    }
    @PostMapping("deleted/{id}")
    public String deleted(@PathVariable("id") int id) {
        userProfileDAO.deleteUser(id);
        return "redirect:/auth/login";
    }


}

package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    public UserProfileController(UserProfileDAO userProfileDAO) {
        this.userProfileDAO = userProfileDAO;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userProfileDAO.getAllUsers());
        System.out.println("index");
        return "userprofile/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("userprofile", userProfileDAO.getUserById(id));
        return "userprofile/show";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("userprofile", userProfileDAO.getUserById(id));
        System.out.println("get-edit");
        return "userprofile/edit";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("userprofile") UserProfile userProfile, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        System.out.println("post-edit");
        if (bindingResult.hasErrors())
            return "userprofile/edit";

        userProfileDAO.updateUser(id, userProfile);
        return "redirect:/userprofile";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") int id) {
        userProfileDAO.deleteUser(id);
        return "redirect:/userprofile";
    }


}

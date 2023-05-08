package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.FollowersDAO;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;

import java.security.Principal;
import java.util.List;

@Controller
public class FollowersController {

    private final FollowersDAO followersDao;
    private final UserProfileDAO userProfileDAO;

    @Autowired
    public FollowersController(FollowersDAO followersDao, UserProfileDAO userProfileDAO) {
        this.followersDao = followersDao;
        this.userProfileDAO = userProfileDAO;
    }

    @PostMapping("/followers/add")
    public String addFollower(@RequestParam String studentEmail, Principal principal) {
        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());
        List<String> followers = followersDao.getFollowing(user.getEmail());

        if (followers.contains(studentEmail)) {
            followersDao.removeFollowing(user.getEmail(), studentEmail);
        } else {
            followersDao.addFollower(studentEmail, user.getEmail());
        }

        return "redirect:/posts";
    }


    @PostMapping("/followers/remove")
    public String removeFollower(@RequestParam String studentEmail, @RequestParam String followerEmail) {
        followersDao.removeFollower(studentEmail, followerEmail);
        return "redirect:/followers?studentEmail=" + studentEmail;
    }

    @GetMapping("/followers")
    public String getFollowers(Model model, @RequestParam String studentEmail) {
        List<String> followers = followersDao.getFollowers(studentEmail);
        model.addAttribute("followers", followers);
        model.addAttribute("studentEmail", studentEmail);
        return "followers";
    }

    @GetMapping("/following")
    public String getFollowing(Model model, @RequestParam String followerEmail) {
        List<String> following = followersDao.getFollowing(followerEmail);
        model.addAttribute("following", following);
        model.addAttribute("followerEmail", followerEmail);
        return "following";
    }
}



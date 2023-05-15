package ru.alishev.springcourse.FirstSecurityApp.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.alishev.springcourse.FirstSecurityApp.models.PostLike;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PostLikeDAO;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;

import java.security.Principal;
import java.util.List;

@Controller
public class PostLikeController {


    private PostLikeDAO postLikeDAO;
    private final UserProfileDAO userProfileDAO;
    @Autowired
    public PostLikeController(PostLikeDAO postLikeDAO, UserProfileDAO userProfileDAO) {
        this.postLikeDAO = postLikeDAO;
        this.userProfileDAO = userProfileDAO;
    }


    @PostMapping("/posts/{postId}/like")
    public String addPostLike(@PathVariable("postId") int postId, String userEmail, Principal principal) {
        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());

        if(postLikeDAO.getPostLikeByEmail(postId,user.getEmail())==true){
            postLikeDAO.deletePostLike(postId, user.getEmail());
        }else {
            postLikeDAO.addPostLike(postId, user.getEmail());
        }

        return "redirect:/posts";
    }

    @PostMapping("/posts/{postId}/likeAdmin")
    public String addPostLikeAdmin(@PathVariable("postId") int postId, String userEmail, Principal principal) {
        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());

        if(postLikeDAO.getPostLikeByEmail(postId,user.getEmail())==true){
            postLikeDAO.deletePostLike(postId, user.getEmail());
        }else {
            postLikeDAO.addPostLike(postId, user.getEmail());
        }

        return "redirect:/posts/adminPosts";
    }


    @PostMapping("/posts/{postId}/unlike")
    public String deletePostLike(@PathVariable("postId") int postId, String userEmail) {
        postLikeDAO.deletePostLike(postId, userEmail);
        return "redirect:/posts/" + postId;
    }

    @GetMapping("/posts/{postId}/likes")
    public String getPostLikes(@PathVariable("postId") int postId, Model model) {
        List<PostLike> postLikes = postLikeDAO.getPostLikes(postId);
        model.addAttribute("postLikes", postLikes);
        return "likes/post_likes";
    }

}

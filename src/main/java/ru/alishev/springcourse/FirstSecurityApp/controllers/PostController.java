package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.models.Post;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PostDAO;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostDAO postDAO;
    private final UserProfileDAO userProfileDAO;

    @Autowired
    public PostController(PostDAO postDAO, UserProfileDAO userProfileDAO) {
        this.postDAO = postDAO;
        this.userProfileDAO = userProfileDAO;
    }

    @GetMapping("/adminPosts")
    public String getAllPostsAdmin(Model model) {
        List<Post> posts = postDAO.getAllPosts();
        model.addAttribute("posts", posts);
        return "posts/adminPosts";
    }

    @GetMapping()
    public String getAllPosts(Model model, Principal principal) {
        List<Post> posts = postDAO.getAllPosts();
        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());

        model.addAttribute("posts", posts);
        model.addAttribute("email", user.getEmail());
        return "posts/index";
    }



    @GetMapping("/new")
    public String addPostForm(Model model, Principal principal) {

        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());
        Post newPost = new Post();
        newPost.setEmail(user.getEmail());
        model.addAttribute("post", newPost);
        return "posts/new";
    }


    @PostMapping("/new")
    public String addPost(@ModelAttribute("post") Post post) {
        postDAO.addPost(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String editPostForm(@PathVariable("id") int id, Model model) {
        Post post = postDAO.getPostById(id);
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable("id") int id, @ModelAttribute("post") Post updatedPost) {
        postDAO.updatePost(id, updatedPost);
        return "redirect:/posts";
    }

    @PostMapping ("/delete/{id}")
    public String deletePost(@PathVariable("id") int id) {
        postDAO.deletePost(id);
        return "redirect:/posts";
    }
    @PostMapping ("/deleteadmin/{id}")
    public String deleteAdminPost(@PathVariable("id") int id) {
        postDAO.deletePost(id);
        return "redirect:/posts/adminPosts";
    }
}

package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.FirstSecurityApp.models.Follower;
import ru.alishev.springcourse.FirstSecurityApp.models.Post;
import ru.alishev.springcourse.FirstSecurityApp.models.PostComment;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostDAO postDAO;
    private final UserProfileDAO userProfileDAO;
    private final PostLikeDAO postLikeDAO;

    private final PostCommentDAO postCommentDAO;

    private final FollowersDAO followersDAO;

    @Autowired
    public PostController(PostDAO postDAO, UserProfileDAO userProfileDAO, PostLikeDAO postLikeDAO, PostCommentDAO postCommentDAO, FollowersDAO followersDAO) {
        this.postDAO = postDAO;
        this.userProfileDAO = userProfileDAO;
        this.postLikeDAO = postLikeDAO;
        this.postCommentDAO = postCommentDAO;
        this.followersDAO = followersDAO;
    }

    @GetMapping("/adminPosts")
    public String getAllPostsAdmin(Model model, Principal principal) {
        List<Post> posts = postDAO.getAllPosts();
        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());
        List<String> followers = followersDAO.getFollowing(user.getEmail());
        System.out.println(followers.size());

        for (Post post : posts) {
            List<PostComment> comments = postCommentDAO.getAllCommentsByPostId(post.getPostId());
            post.setNumComments(comments.size());

            int numLikes = postLikeDAO.getPostLikes(post.getPostId()).size();
            post.setNumLikes(numLikes);


            if(postLikeDAO.getPostLikeByEmail(post.getPostId(),user.getEmail())==true) {
                post.setLiked(true);
            }else{
                post.setLiked(false);
            }
        }


        model.addAttribute("followers", followers);
        model.addAttribute("posts", posts);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("user", user);
        return "posts/adminPosts";
    }



    @GetMapping()
    public String getAllPosts(Model model, Principal principal) {
        List<Post> posts = postDAO.getAllPosts();
        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());
        List<String> followers = followersDAO.getFollowing(user.getEmail());
        System.out.println(followers.size());

        for (Post post : posts) {
            List<PostComment> comments = postCommentDAO.getAllCommentsByPostId(post.getPostId());
            post.setNumComments(comments.size());

            int numLikes = postLikeDAO.getPostLikes(post.getPostId()).size();
            post.setNumLikes(numLikes);


            if(postLikeDAO.getPostLikeByEmail(post.getPostId(),user.getEmail())==true) {
                post.setLiked(true);
            }else{
                post.setLiked(false);
            }
        }


        model.addAttribute("followers", followers);
        model.addAttribute("posts", posts);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("user", user);
        return "posts/index";
    }



    @PostMapping("/search")
    public String searchPostsByEmail(@RequestParam("email") String email, Model model, Principal principal) {
        List<Post> posts = postDAO.getAllPostsByEmail(email);
        UserProfile user = userProfileDAO.getUserByEmail(principal.getName());
        List<String> followers = followersDAO.getFollowing(user.getEmail());

        for (Post post : posts) {
            List<PostComment> comments = postCommentDAO.getAllCommentsByPostId(post.getPostId());
            post.setNumComments(comments.size());

            int numLikes = postLikeDAO.getPostLikes(post.getPostId()).size();
            post.setNumLikes(numLikes);


            if(postLikeDAO.getPostLikeByEmail(post.getPostId(),user.getEmail())==true) {
                post.setLiked(true);
            }else{
                post.setLiked(false);
            }
        }

        model.addAttribute("followers", followers);
        model.addAttribute("posts", posts);
        model.addAttribute("email", user.getEmail());
        model.addAttribute("user", user);
        return "posts/search";
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

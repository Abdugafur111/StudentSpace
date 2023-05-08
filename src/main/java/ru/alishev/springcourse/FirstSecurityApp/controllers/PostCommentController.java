package ru.alishev.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.alishev.springcourse.FirstSecurityApp.models.Post;
import ru.alishev.springcourse.FirstSecurityApp.models.PostComment;
import ru.alishev.springcourse.FirstSecurityApp.models.UserProfile;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PostCommentDAO;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PostDAO;
import ru.alishev.springcourse.FirstSecurityApp.repositories.UserProfileDAO;

import java.security.Principal;
import java.util.List;

@Controller
public class PostCommentController {

    private final PostCommentDAO postCommentDAO;
    private final UserProfileDAO userProfileDAO;
    private final PostDAO postDAO;


    @Autowired
    public PostCommentController(PostCommentDAO postCommentDAO, UserProfileDAO userProfileDAO, PostDAO postDAO) {
        this.postCommentDAO = postCommentDAO;
        this.userProfileDAO = userProfileDAO;
        this.postDAO = postDAO;
    }

    @GetMapping("/post/{postId}/comments")
    public String showComments(@PathVariable int postId, Model model, Principal principal) {
        Post post = postDAO.getPostById(postId);
        List<Post> posts = postDAO.getAllPosts();
        posts.remove(post);
        model.addAttribute("posts", posts);
        model.addAttribute("post", post);
        model.addAttribute("comments", postCommentDAO.getAllCommentsByPostId(postId));
        model.addAttribute("postId", postId);
        if (principal != null) {
            UserProfile user = userProfileDAO.getUserByEmail(principal.getName());
            model.addAttribute("userEmail", user.getEmail());
        }
        return "comments/comments";
    }


    @PostMapping("/post/{postId}/comments/add")
    public String addComment(@PathVariable int postId, PostComment postComment, @RequestParam("userEmail") String userEmail) {

        postComment.setPostId(postId);
        postComment.setPostCommentUserEmail(userEmail);
        postCommentDAO.save(postComment);
        return "redirect:/post/" + postId + "/comments";
    }


    @PostMapping("/comment/{commentId}/update")
    public String updateComment(@PathVariable int commentId, PostComment postComment) {
        postComment.setPostCommentId(commentId);
        postCommentDAO.updateComment(postComment);
        return "redirect:/post/" + postComment.getPostId() + "/comments";
    }

    @PostMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable int commentId, PostComment postComment) {
        postComment.setPostCommentId(commentId);
        int postId = postCommentDAO.deleteCommentById(commentId);
        return "redirect:/post/" + postId + "/comments";
    }
}

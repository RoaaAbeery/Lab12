package com.example.lab12.Controller;

import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;
    @GetMapping("/all-Blogs")
    public ResponseEntity getAllBlogs(){
        return ResponseEntity.status(200).body(blogService.getAllBlog());
    }


    @GetMapping("/my-Blog")
    public ResponseEntity getMyBlog(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(blogService.getMyBlogs(user.getId()));
    }

    @GetMapping("/gtBlogById/{id}")
    public ResponseEntity gtBlogById(@PathVariable Integer id , @AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getBlogById(id,user.getId()));
    }
    @GetMapping("/gtBlogByTitle/{title}")
    public ResponseEntity gtBlogByTitle(@PathVariable String title , @AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(blogService.getBlogByTitle(title, user.getId()));
    }

    @PostMapping("/add-Blogs")
    public ResponseEntity addBlog(@RequestBody @Valid Blog blog, @AuthenticationPrincipal User user){
        blogService.addBlog(blog, user.getId());
        return ResponseEntity.status(201).body("Blog Added");
    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@RequestBody @Valid Blog blog, @PathVariable Integer id, @AuthenticationPrincipal User user){
        blogService.updateBlog(id,blog, user.getId());
        return ResponseEntity.status(200).body("Blog Updated");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable Integer id, @AuthenticationPrincipal  User user){
       blogService.deleteeBlog(id, user.getId());
        return ResponseEntity.status(200).body("Blog deleted");
    }
}

package com.example.lab12.Service;

import com.example.lab12.API.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.BlogRepository;
import com.example.lab12.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final UserRepository userRepository;

    public List getAllBlog(){
        return blogRepository.findAll();
    }
    public Blog getBlogById(Integer id,Integer user_id){
        Blog blog=blogRepository.findBlogById(id);
        if(blog==null){
            throw new ApiException("Blog not found");
        }
        if(blog.getUser().getId()!=user_id){
            throw new ApiException("you do not have the authority");
        }
        return blog;
    }
    public List getMyBlogs(Integer user_id){
        User user=userRepository.findUsersById(user_id);
        List<Blog> blogs=blogRepository.findAllByUser(user);
        if(blogs==null){
            throw new ApiException("Blog Empty");
        }
        return blogs;
    }
    public Blog getBlogByTitle(String title,Integer user_id){
        Blog blog=blogRepository.findBlogByTitle(title);
        if(blog==null){
            throw new ApiException("Blog not found");
        }
        if(blog.getUser().getId()!=user_id){
            throw new ApiException("you do not have the authority");
        }
        return blog;
    }
    public void addBlog(Blog blog,Integer user_id){
        User user =userRepository.findUsersById(user_id);
        blog.setUser(user);
        blogRepository.save(blog);

    }
    public void updateBlog(Integer id, Blog blog,Integer user_id){
        Blog oldBlog=blogRepository.findBlogById(id);
        User user=userRepository.findUsersById(user_id);
        if(oldBlog==null){
            throw new ApiException("Blog not found");
        }
        else if(oldBlog.getUser().getId()!=user_id){
            throw new ApiException("you dont have authority");
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }
    public void deleteeBlog(Integer id,Integer user_id){
        Blog oldBlog=blogRepository.findBlogById(id);
//        User user=userRepository.findUsersById(user_id);
        if(oldBlog==null){
            throw new ApiException("Blog not found");
        }
        else if(oldBlog.getUser().getId()!=user_id){
            throw new ApiException("you dont have authority");
        }
        blogRepository.delete(oldBlog);
}}

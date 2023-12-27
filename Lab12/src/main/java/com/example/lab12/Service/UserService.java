package com.example.lab12.Service;

import com.example.lab12.API.ApiException;
import com.example.lab12.Model.User;
import com.example.lab12.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        user.setRole("USER");
        String hashPass= new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPass);
        userRepository.save(user);
    }
    public void deleteUser(Integer id){
        User user=userRepository.findUsersById(id);
        if(user==null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }
    public void updateUser(User user,Integer id){
        User oldUser=userRepository.findUsersById(id);
        if(oldUser==null){
            throw new ApiException("uer not found");
        }
        user.setRole(oldUser.getRole());
        user.setUsername(oldUser.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
         userRepository.save(user);
    }
}

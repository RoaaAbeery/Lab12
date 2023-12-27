package com.example.lab12.Controller;

import com.example.lab12.Model.User;
import com.example.lab12.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;



    @GetMapping("/all-users")
    public ResponseEntity getUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/addUser")
    public ResponseEntity addUser(@Valid @RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.status(201).body("User Created");
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody @Valid User user, @AuthenticationPrincipal User auth){
        userService.updateUser(user, auth.getId());
        return ResponseEntity.status(200).body("User Updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body("User Deleted");
    }
}

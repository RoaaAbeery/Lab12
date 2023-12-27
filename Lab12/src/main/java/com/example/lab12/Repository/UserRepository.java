package com.example.lab12.Repository;

import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findUsersByUsername(String username);
    User findUsersById(Integer id);
}

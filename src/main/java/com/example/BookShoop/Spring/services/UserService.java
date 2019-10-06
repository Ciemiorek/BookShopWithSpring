package com.example.BookShoop.Spring.services;

import com.example.BookShoop.Spring.models.User;
import com.example.BookShoop.Spring.repositories.imp.UserRepositoryIMP;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {

    private UserRepositoryIMP userRepositoryIMP;

    public UserService(UserRepositoryIMP userRepositoryIMP) {
        this.userRepositoryIMP = userRepositoryIMP;
    }

    public List<User> getUsers() {
        return userRepositoryIMP.getUsers();
    }

    public User addUser(User user){
        return userRepositoryIMP.addUser(user);
    }

    public User getUser(int id) {
        return userRepositoryIMP.getUser(id);
    }

    public boolean deleteUser(int id) {
        return userRepositoryIMP.deleteUser(id);
    }
}

package com.mia.eatgo.application;

import com.mia.eatgo.domain.User;
import com.mia.eatgo.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User addUser(User user) {

        return userRepository.save(user);
    }

    public User updateUser(Long id, String name, String email, Long level) {
        User user = userRepository.findById(id).orElse(null);
        user.updateUser(name, email, level);

        return user;

    }

    public User deactiveUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        user.deactivate();
        return user;
    }
}

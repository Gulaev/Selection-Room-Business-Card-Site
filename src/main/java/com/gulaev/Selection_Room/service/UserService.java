package com.gulaev.Selection_Room.service;

import com.gulaev.Selection_Room.model.User;
import com.gulaev.Selection_Room.repository.UserRepository;
import com.gulaev.Selection_Room.security.UserDetailsImp;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id){
        return userRepository.getOne(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    @Override
    public UserDetailsImp loadUserByUsername(String s)  throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByFirstName(s);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");

        } else {
            return new UserDetailsImp(user.get());
        }
    }
}

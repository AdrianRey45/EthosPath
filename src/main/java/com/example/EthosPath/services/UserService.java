package com.example.EthosPath.services;

import com.example.EthosPath.model.entity.User;
import com.example.EthosPath.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        
        user.setLevel(1);
        user.setCurrentXp(0);
        user.setTotalXp(0);
        
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        return userRepository.findByEmail(email)
            .filter(u -> u.getPassword().equals(password))
            .orElseThrow(() -> new RuntimeException("Credenciales incorrectas"));
    }

    public User findById(String id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public void recoverPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Email no encontrado"));
        user.setPassword(newPassword);
        userRepository.save(user);
    }

    public Optional<User> getUserProfile(String id) {
        return userRepository.findById(id);
    }

    public void addExperience(String userId, Integer xpToAdd) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        
        int currentXp = (user.getCurrentXp() != null) ? user.getCurrentXp() : 0;
        int totalXp = (user.getTotalXp() != null) ? user.getTotalXp() : 0;

        user.setCurrentXp(currentXp + xpToAdd);
        user.setTotalXp(totalXp + xpToAdd);
        
        checkLevelUp(user);
        userRepository.save(user);
    }

    private void checkLevelUp(User user) {
        int level = (user.getLevel() != null) ? user.getLevel() : 1;
        int xpNeeded = level * 100; 
        
        while (user.getCurrentXp() >= xpNeeded) {
            user.setCurrentXp(user.getCurrentXp() - xpNeeded);
            level++;
            user.setLevel(level);
            xpNeeded = level * 100; 
        }
    }

    public void addFriend(String userId, String friendId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        User friend = userRepository.findById(friendId)
            .orElseThrow(() -> new RuntimeException("Amigo no encontrado"));
        
        user.getFriends().add(friend);
        userRepository.save(user);
    }

    public Set<User> getFriends(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return user.getFriends();
    }
}
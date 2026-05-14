package com.example.EthosPath.controller;

import com.example.EthosPath.model.dto.AuthRequest;
import com.example.EthosPath.model.dto.RegistrationRequest;
import com.example.EthosPath.model.dto.UserResponse;
import com.example.EthosPath.model.entity.User;
import com.example.EthosPath.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody RegistrationRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setLevel(1);
        user.setCurrentXp(0);
        user.setTotalXp(0);

        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(convertToResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody AuthRequest request) {
        User user = userService.login(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(convertToResponse(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable String id) {
        return userService.getUserProfile(id)
                .map(user -> ResponseEntity.ok(convertToResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{userId}/friends/{friendId}")
    public ResponseEntity<Void> addFriend(@PathVariable String userId, @PathVariable String friendId) {
        userService.addFriend(userId, friendId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/friends")
    public ResponseEntity<Set<UserResponse>> getFriends(@PathVariable String userId) {
        Set<UserResponse> friends = userService.getFriends(userId).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toSet());
        return ResponseEntity.ok(friends);
    }

    private UserResponse convertToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setLevel(user.getLevel());
        response.setCurrentXp(user.getCurrentXp());
        response.setTotalXp(user.getTotalXp());
        return response;
    }
}
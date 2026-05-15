package com.example.EthosPath.controller;

import com.example.EthosPath.model.dto.AuthRequest;
import com.example.EthosPath.model.dto.RegistrationRequest;
import com.example.EthosPath.model.dto.UserResponse;
import com.example.EthosPath.model.entity.User;
import com.example.EthosPath.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        try {
            User user = new User();
            user.setUsername(request.getUsername());
            user.setEmail(request.getEmail());
            user.setPassword(request.getPassword());
            user.setLevel(1);
            user.setCurrentXp(0);
            user.setTotalXp(0);

            User savedUser = userService.registerUser(user);
            return ResponseEntity.ok(convertToResponse(savedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            User user = userService.login(request.getEmail(), request.getPassword());
            return ResponseEntity.ok(convertToResponse(user));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserResponse>> searchUsers(@RequestParam String username) {
        List<UserResponse> users = userService.searchUsers(username).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable String id) {
        return userService.getUserProfile(id)
                .map(user -> ResponseEntity.ok(convertToResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getProfileByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(user -> ResponseEntity.ok(convertToResponse(user)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getProfileByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
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
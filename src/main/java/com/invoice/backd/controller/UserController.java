package com.invoice.backd.controller;

import com.invoice.backd.entity.User;
import com.invoice.backd.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public User saveOrUpdateUser(@RequestBody User user, Authentication authentication) {
        try{
            if(!authentication.getName().equals(user.getClerkId())){
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have permission");
            }
            return userService.SaveOrUpdateUser(user);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    /* ================= GET USER BY CLERK ID ================= */
    @GetMapping("/{clerkId}")
    public ResponseEntity<User> getUserByClerkId(@PathVariable String clerkId) {
        User user = userService.getAccountByClerkId(clerkId);
        return ResponseEntity.ok(user);
    }

    /* ================= DELETE USER ================= */
    @DeleteMapping("/{clerkId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String clerkId) {
        userService.deleteAcound(clerkId);
        return ResponseEntity.noContent().build();
    }
}
package com.example.demo.controller;

import com.example.demo.constants.Constants;
import com.example.demo.entities.User;
import com.example.demo.entities.VerificationToken;
import com.example.demo.exception.UserRegistrationException;
import com.example.demo.model.AuthenticationRequest;
import com.example.demo.response.AuthenticationResponse;
import com.example.demo.services.JwtService;
import com.example.demo.services.UserService;
import com.example.demo.services.VerificationService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final VerificationService verificationService;

    private final UserService userService;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserController(VerificationService verificationService, UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.verificationService = verificationService;
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    @PreAuthorize(Constants.HAS_ANY_ROLE)
    public ResponseEntity<User> registerUser(@RequestBody @Validated User user) {
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

//        System.out.println(user);
        userService.addUser(user, passwordEncoder);
//        VerificationToken token = verificationService.getVerificationToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
//    @GetMapping
//    @PreAuthorize(Constants.HAS_ROLE_MEMBER_AND_ABOVE)
//    public ResponseEntity<User> getUserInfo() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = (String) authentication.getPrincipal();
//        return ResponseEntity.ok(userService.getUserByEmail(email));
//    }
//
    @PostMapping("/login")
    @PreAuthorize(Constants.HAS_ANY_ROLE)
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()
            ));
        } catch (BadCredentialsException e) {
            throw new UserRegistrationException("Incorrect email or password", HttpStatus.UNAUTHORIZED);
        }
        User user = userService.loadUserByUsername(request.getEmail());
        System.out.println(user);
        String jwtToken = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthenticationResponse(jwtToken, user));
    }

    @GetMapping
    @PreAuthorize(Constants.HAS_ROLE_USER)
    public User findPerson(){
        return userService.getAllUsers().get(0);
    }
//
//    @GetMapping("/verify")
//    @PreAuthorize(Constants.HAS_ANY_ROLE)
//    public ResponseEntity<String> verifyUser(@RequestParam UUID token) {
//        userService.verifyUser(token);
//        return ResponseEntity.ok("Verification successful!");
//    }
//
//    @PostMapping("/assign/role")
//    @PreAuthorize(Constants.HAS_ROLE_CORE_AND_ABOVE)
//    public ResponseEntity<String> assignRole(@RequestBody AssignRoleRequest assignRoleRequest) {
//        return ResponseEntity.ok(userService.changeRole(assignRoleRequest));
//    }
//
//    @GetMapping("/{userId}")
//    @PreAuthorize(Constants.HAS_ROLE_MEMBER_AND_ABOVE)
//    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
//        return ResponseEntity.ok(userService.getUserById(userId));
//    }
//
//    @GetMapping("/rank")
//    public ResponseEntity<Long> getRank(@RequestParam @NonNull Integer score) {
//        return ResponseEntity.ok(userService.getRank(score));
//    }
//
//    @GetMapping("/leaderboard")
//    public ResponseEntity<List<User>> getLeaderboard(@RequestParam @Nullable Integer offset, @RequestParam @Nullable Integer pageSize) {
//        if (offset == null) offset = 0;
//        if (pageSize == null) pageSize = 20; // returning first 20 users
//
//        if (offset < 0) throw new AcmException("offset cannot be < 0", HttpStatus.BAD_REQUEST);
//        if (pageSize <= 0) throw new AcmException("pageSize must be >= 0", HttpStatus.BAD_REQUEST);
//
//        return ResponseEntity.ok(userService.getLeaderboard(offset, pageSize));
//    }
//
//    @GetMapping("/leaderboard/{batch}")
//    public ResponseEntity<List<User>> getLeaderboardByBatch(@PathVariable Integer batch, @RequestParam @Nullable Integer offset, @RequestParam @Nullable Integer pageSize) {
//        if (offset == null) offset = 0;
//        if (pageSize == null) pageSize = 20; // returning first 20 users
//
//        if (offset < 0) throw new AcmException("offset cannot be < 0", HttpStatus.BAD_REQUEST);
//        if (pageSize <= 0) throw new AcmException("pageSize must be >= 0", HttpStatus.BAD_REQUEST);
//
//        List<User> users = userService.getLeaderboardByBatch(batch, offset, pageSize);
//        return ResponseEntity.ok(users);
//    }
//
//    @PutMapping
//    @PreAuthorize(Constants.HAS_ROLE_MEMBER_AND_ABOVE)
//    public ResponseEntity<User> updateUser(@RequestBody User user) {
//        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        User updatedUser = userService.updateUser(user, email);
//        return (updatedUser == null) ? ResponseEntity.badRequest().build() : ResponseEntity.ok(updatedUser);
//    }
//
//    @GetMapping("/transaction")
//    @PreAuthorize(Constants.HAS_ROLE_MEMBER_AND_ABOVE)
//    public ResponseEntity<List<Transaction>> getUserTransactions(@RequestParam @Nullable Integer offset, @RequestParam @Nullable Integer pageSize) {
//        if (offset == null) offset = 0;
//        if (pageSize == null) pageSize = 20; // returning first 20 transactions
//
//        if (offset < 0) throw new AcmException("offset cannot be < 0", HttpStatus.BAD_REQUEST);
//        if (pageSize <= 0) throw new AcmException("pageSize must be >= 0", HttpStatus.BAD_REQUEST);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = (String) authentication.getPrincipal();
//        return ResponseEntity.ok(userService.getUserTransactions(email, offset, pageSize));
//    }
//
//    @GetMapping("/events")
//    @PreAuthorize(Constants.HAS_ROLE_MEMBER_AND_ABOVE)
//    public ResponseEntity<List<UserEventDetails>> getEventsForUser(@RequestParam @Nullable EventRole eventRole, @RequestParam @Nullable Integer pageSize, @RequestParam @Nullable Integer offset) {
//        if (offset == null) offset = 0;
//        if (pageSize == null) pageSize = 20; // returning first 20 users
//
//        if (offset < 0) throw new AcmException("offset cannot be < 0", HttpStatus.BAD_REQUEST);
//        if (pageSize <= 0) throw new AcmException("pageSize must be >= 0", HttpStatus.BAD_REQUEST);
//
//        if (eventRole != null) {
//            return ResponseEntity.ok(userService.getEventsForUserWithRole(eventRole, pageSize, offset));
//        }
//
//        return ResponseEntity.ok(userService.getEventsForUser(pageSize, offset));
//
//    }
}
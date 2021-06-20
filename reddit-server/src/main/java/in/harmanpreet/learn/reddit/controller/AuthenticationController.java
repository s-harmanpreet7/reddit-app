package in.harmanpreet.learn.reddit.controller;

import in.harmanpreet.learn.reddit.dto.RegisterRequest;
import in.harmanpreet.learn.reddit.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration Successful", OK);
    }

    @GetMapping("activate/{token}")
    public ResponseEntity activateAccount(@PathVariable String token) {
        authService.activateAccount(token);
        return new ResponseEntity<>("User account activated successfully.", OK);
    }
}

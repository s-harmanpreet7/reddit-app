package in.harmanpreet.learn.reddit.controller;

import in.harmanpreet.learn.reddit.dto.RegisterRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @PostMapping("/signup")
    public void signup(@RequestBody RegisterRequest registerRequest) {

    }
}

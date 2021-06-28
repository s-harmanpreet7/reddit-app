package in.harmanpreet.learn.reddit.service;

import in.harmanpreet.learn.reddit.dto.PostRequest;
import in.harmanpreet.learn.reddit.dto.PostResponse;
import in.harmanpreet.learn.reddit.exception.PostNotFoundException;
import in.harmanpreet.learn.reddit.exception.SubredditNotFoundException;
import in.harmanpreet.learn.reddit.mapper.PostMapper;
import in.harmanpreet.learn.reddit.model.Post;
import in.harmanpreet.learn.reddit.model.Subreddit;
import in.harmanpreet.learn.reddit.model.User;
import in.harmanpreet.learn.reddit.repository.PostRepository;
import in.harmanpreet.learn.reddit.repository.SubredditRepository;
import in.harmanpreet.learn.reddit.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {
    private final AuthService authService;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SubredditRepository subredditRepository;

    public Post save(PostRequest postRequest) {
        Subreddit subreddit = subredditRepository.findByName(postRequest.getSubredditName())
                .orElseThrow(() -> new SubredditNotFoundException(postRequest.getSubredditName()));

        User user = authService.getCurrentUser();

        return postMapper.map(postRequest, subreddit, user);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));
        return postMapper.mapToDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
        List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts.stream().map(postMapper::mapToDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::mapToDto)
                .collect(Collectors.toList());
    }
}

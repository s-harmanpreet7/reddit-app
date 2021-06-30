package in.harmanpreet.learn.reddit.service;

import in.harmanpreet.learn.reddit.dto.CommentsDto;
import in.harmanpreet.learn.reddit.enums.MailTemplate;
import in.harmanpreet.learn.reddit.exception.PostNotFoundException;
import in.harmanpreet.learn.reddit.mapper.CommentMapper;
import in.harmanpreet.learn.reddit.model.Comment;
import in.harmanpreet.learn.reddit.model.NotificationEmail;
import in.harmanpreet.learn.reddit.model.Post;
import in.harmanpreet.learn.reddit.model.User;
import in.harmanpreet.learn.reddit.repository.CommentRepository;
import in.harmanpreet.learn.reddit.repository.PostRepository;
import in.harmanpreet.learn.reddit.repository.UserRepository;
import in.harmanpreet.learn.reddit.util.MailContentBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentsService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final MailContentBuilder mailContentBuilder;
    private final MailService mailService;

    private final String POST_URL = "";

    public void save(CommentsDto commentsDto) {
        Post post = postRepository.findById(commentsDto.getPostId())
                .orElseThrow(() -> new PostNotFoundException(commentsDto.getPostId().toString()));
        Comment comment = commentMapper.map(commentsDto, post, authService.getCurrentUser());
        commentRepository.save(comment);

        String message = mailContentBuilder.build(MailTemplate.COMMENT_NOTIFICATION,
                post.getUser().getUsername() + "posted a comment on your post." + POST_URL);
        sendCommentNotification(message, post.getUser());

    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(MailTemplate.COMMENT_NOTIFICATION,
                new NotificationEmail(user.getUsername() + " commented on your post", user.getEmail(), message));
    }

    public List<CommentsDto> getAllCommentsForPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException(postId.toString()));

        return commentRepository.findByPost(post).stream()
                .map(commentMapper::mapToDto)
                .collect(Collectors.toList());
    }
}

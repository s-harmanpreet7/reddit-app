package in.harmanpreet.learn.reddit.repository;

import in.harmanpreet.learn.reddit.model.Comment;
import in.harmanpreet.learn.reddit.model.Post;
import in.harmanpreet.learn.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    List<Comment> findAllByUser(User user);
}

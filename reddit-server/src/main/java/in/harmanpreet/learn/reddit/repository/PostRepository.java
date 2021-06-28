package in.harmanpreet.learn.reddit.repository;

import in.harmanpreet.learn.reddit.model.Post;
import in.harmanpreet.learn.reddit.model.Subreddit;
import in.harmanpreet.learn.reddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}

package in.harmanpreet.learn.reddit.mapper;

import in.harmanpreet.learn.reddit.dto.PostRequest;
import in.harmanpreet.learn.reddit.dto.PostResponse;
import in.harmanpreet.learn.reddit.model.Post;
import in.harmanpreet.learn.reddit.model.Subreddit;
import in.harmanpreet.learn.reddit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")

    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "userName", source = "user.username")
    PostResponse mapToDto(Post post);
}

package proj2.backend.implementation;

import java.util.List;

import proj2.backend.model.BlogComment;
import proj2.backend.model.BlogPost;
import proj2.backend.model.User;


public interface BlogDao {
	List<BlogPost> getBlogPosts();
	BlogPost getBlogPost(int id);
	BlogPost addBlogPost(User user,BlogPost blogPost);
	List<BlogComment> getBlogComments(int blogId);
	BlogPost addBlogPostComment(User user,BlogComment blogComment);
}
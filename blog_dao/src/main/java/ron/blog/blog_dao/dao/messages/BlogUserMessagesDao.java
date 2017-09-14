package ron.blog.blog_dao.dao.messages;

import ron.blog.blog_domain.messages.BlogUserMessages;

public interface BlogUserMessagesDao {
    int deleteByPrimaryKey(String uid);

    int insert(BlogUserMessages record);

    BlogUserMessages selectByPrimaryKey(String uid);

    int updateByPrimaryKey(BlogUserMessages record);
}
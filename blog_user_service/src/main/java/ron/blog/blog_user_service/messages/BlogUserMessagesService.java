package ron.blog.blog_user_service.messages;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ron.blog.blog_common.constant.UserMessagesEnum;
import ron.blog.blog_common.utils.IdGenerator;
import ron.blog.blog_dao.dao.messages.BlogUserMessagesDao;
import ron.blog.blog_domain.messages.BlogUserMessages;
import ron.blog.blog_facade.messages.BlogUserMessagesFacade;

@Component("blogUserMessagesService")
public class BlogUserMessagesService implements BlogUserMessagesFacade {

	@Autowired
	BlogUserMessagesDao blogUserMessagesDao;
	
	/**
	 * @Comment 插入信息
	 * @Author Ron
	 * @Date 2017年9月14日 下午5:17:47
	 * @return
	 */
	@Override
	public int insertMsg(BlogUserMessages messages) {
		String uid = IdGenerator.genUUID();
		messages.setCrtTime(new Date());
		messages.setStatus(UserMessagesEnum.Status.NOT_READ.getValue());
		messages.setUid(uid);
		return blogUserMessagesDao.insert(messages);
	}
}

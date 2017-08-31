package ron.blog.blog_pc.controller.blog;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_domain.user.BlogUserBase;
import ron.blog.blog_facade.user.UserBaseFacade;
import ron.blog.blog_pc.controller.base.BaseController;

/**
 * @Comment Blog控制器
 * @Author Ron
 * @Date 2017年8月22日 下午3:23:28
 * @return
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController {
	
	@Autowired
	UserBaseFacade userBaseService;
	
	/**
	 * @Comment 博客列表
	 * @Author Ron
	 * @Date 2017年8月22日 下午3:54:38
	 * @return
	 */
	@RequestMapping(value={"/list","/"})
	public String blogList(HttpServletRequest request) {
		return "blog/list";
	}
}

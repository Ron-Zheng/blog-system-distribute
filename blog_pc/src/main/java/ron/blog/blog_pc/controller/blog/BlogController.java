package ron.blog.blog_pc.controller.blog;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_domain.comment.BlogComment;
import ron.blog.blog_facade.comment.BlogCommetFacade;
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
	
	@Autowired
	BlogCommetFacade blogCommetService;
	
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
	
	/**
	 * @Comment 博客详细页面
	 * @Author Ron
	 * @Date 2017年9月5日 下午3:03:03
	 * @return
	 */
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public String blogDetail(HttpServletRequest request){
		List<BlogComment> list= blogCommetService.queryBlogComment("");
		
		request.setAttribute("blogComments", list);
		return "blog/detail";
	}
	
	/**
	 * @Comment 获取评论列表
	 * @Author Ron
	 * @Date 2017年9月5日 下午4:32:23
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/getCommentReply",method=RequestMethod.POST)
	public Resp getCommentReply(HttpServletRequest request){
		return new Resp(ResCode.SUCCESS, blogCommetService.queryBlogCommentReply(""));
	}
}

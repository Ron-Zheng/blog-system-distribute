package ron.blog.blog_pc.controller.messages;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_common.utils.HttpUtils;
import ron.blog.blog_domain.messages.BlogUserMessages;
import ron.blog.blog_facade.messages.BlogUserMessagesFacade;
import ron.blog.blog_pc.controller.base.BaseController;

/**
 * @Comment 用户留言控制器
 * @Author Ron
 * @Date 2017年9月13日 下午5:41:15
 * @return
 */
@RequestMapping("/msg")
@Controller
public class MessagesController extends BaseController {
	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Autowired
	BlogUserMessagesFacade BlogUserMessagesService;
	
	/**
	 * @Comment 发送留言页面
	 * @Author Ron
	 * @Date 2017年9月13日 下午6:19:38
	 * @return
	 */
	@RequestMapping(value="/send",method=RequestMethod.GET)
	public String send(HttpServletRequest request){
		logger.info("进入留言页面");
		return "messages/send";
	}
	
	/**
	 * @Comment 发送消息
	 * @Author Ron
	 * @Date 2017年9月13日 下午6:23:32
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/sendMsg",method=RequestMethod.POST)
	public Resp sendMsg(@Valid BlogUserMessages msg,BindingResult result,HttpServletRequest request){
		String content = request.getParameter("layedit_content");
		if(StringUtils.isEmpty(content)){
			return new Resp(ResCode.DATA_EMPTY, "layedit_content");
		}
		if (result.hasErrors()) {
			return new Resp(ResCode.VALIDATE_FAILED, result.getFieldErrors());
		}else{
			ResCode resCode = ResCode.SUCCESS;
			try {
				String ip = HttpUtils.getIpAddress(request);
				msg.setIp(ip);
				msg.setContent(content);
				BlogUserMessagesService.insertMsg(msg);
			} catch (Exception e) {
				resCode = ResCode.SYS_ERROR;
			}
			return new Resp(resCode, "");
		}
	}
}

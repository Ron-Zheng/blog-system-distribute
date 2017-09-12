package ron.blog.blog_service.user;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ron.blog.blog_common.constant.VerifyCodeEnum;
import ron.blog.blog_common.resp.ResCode;
import ron.blog.blog_common.resp.Resp;
import ron.blog.blog_common.utils.IdGenerator;
import ron.blog.blog_dao.dao.user.BlogVerifyCodeDao;
import ron.blog.blog_domain.user.BlogVerifyCode;
import ron.blog.blog_facade.user.VerifyCodeFacade;
import ron.blog.blog_service.utils.MailSender;

@Component
public class VerifyCodeService implements VerifyCodeFacade {

	private Logger logger=LogManager.getLogger(this.getClass());
	
	@Autowired
	BlogVerifyCodeDao blogVerifyCodeDao;
	
	/**
	 * @Comment 发送验证码
	 * @Author Ron
	 * @Date 2017年9月1日 下午4:58:39
	 * @return
	 */
	@Override
	public String insertVerifyCode(String email,VerifyCodeEnum.type type) {
		String verifyCode = IdGenerator.getRandomNum(6);
		logger.info("验证码为："+verifyCode);
		MailSender.send(email, "Ron博客验证码", "您的验证码为"+verifyCode);
		
		//存储验证码
		String uid = IdGenerator.genUUID();
		BlogVerifyCode vCode = new BlogVerifyCode();
		vCode.setEmail(email);
		vCode.setVerifyCode(verifyCode);
		
		Date now = new Date();
		vCode.setSendTime(now);
		
		Date afterDate = new Date(now .getTime() + 300000);
		
		vCode.setEndTime(afterDate);
		vCode.setRemarks(type.getRemarks());
		vCode.setStatus(VerifyCodeEnum.status.SEND.getValue());
		vCode.setType(type.getValue());
		vCode.setUid(uid);
		
		blogVerifyCodeDao.insert(vCode);
		return uid;
	}

	/**
	 * @Comment 验证验证码
	 * @Author Ron
	 * @Date 2017年9月11日 下午5:19:36
	 * @return
	 */
	@Override
	public Resp checkVerifyCode(String uid, String vCode) {
		try {
			BlogVerifyCode bvCode = blogVerifyCodeDao.selectByPrimaryKey(uid);
			
			if(bvCode != null){
				if(!bvCode.getVerifyCode().equals(vCode)){
					return new Resp(ResCode.VERIFY_CODE_ERROR,"");
				}
				
				if(new Date().getTime() > bvCode.getEndTime().getTime() || bvCode.getStatus()==VerifyCodeEnum.status.VERIFIED.getValue()){
					return new Resp(ResCode.EXPIRED,"");
				}
				
				blogVerifyCodeDao.updateStatusByPrimaryKey(uid, bvCode.getEmail(), VerifyCodeEnum.status.VERIFIED.getValue());
				
				return new Resp(ResCode.SUCCESS,"");
			}else{
				return new Resp(ResCode.VERIFY_CODE_ERROR,"");
			}
		} catch (Exception e) {
			return new Resp(ResCode.SYS_ERROR,"");
		}
	}
}

package ron.blog.blog_user_service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class UserServiceProvider{
	private static final Logger LOG = LogManager.getLogger(UserServiceProvider.class);
	
	public static final String DUBBO_PROVIDER = "classpath:spring-context.xml";
	
	private static void init() {

		LOG.info("开始启动dubo服务，载入的配置服务提供文件为[" + DUBBO_PROVIDER + "]");

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(DUBBO_PROVIDER);
		context.registerShutdownHook();
		context.start();

		LOG.info("【tfs-service-user】启动完毕！");
	}

	public static void main(String[] args) {
		init();
		while (true) {
			try {
				Thread.currentThread();
				Thread.sleep(3L);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}

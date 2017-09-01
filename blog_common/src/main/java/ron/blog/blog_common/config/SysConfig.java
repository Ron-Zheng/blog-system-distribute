package ron.blog.blog_common.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

import ron.blog.blog_common.config.annotation.IgnoreAssignment;

public class SysConfig {
	private static String mail_host;
	private static String mail_address;
	private static String mail_passowrd;
	private static String mail_port;
	
	@IgnoreAssignment
	private static Logger logger = LogManager.getLogger(SysConfig.class);
	
	@IgnoreAssignment
	public static final InputStream fileInput = SysConfig.class.getResourceAsStream("/config.properties");
	
	@IgnoreAssignment
	private static long fileModifyTime;//文件修改时间
	
	@IgnoreAssignment
	private static Properties prop = new Properties(); 
	
	private SysConfig(){}
	
	static{
		load(fileInput);
	}
	
	private static void load(File file){
		fileModifyTime = file.lastModified();//更新时间
		try {
			load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			logger.error("",e);
		}
	}
	
	private static void load(InputStream is){
		try {
			prop.load(is);
		} catch (IOException e) {
			logger.error("",e);
		}  
		//给Config类属性赋值
		Class<SysConfig> configClass = SysConfig.class;
		Field[] fields = configClass.getDeclaredFields();
		for (Field field : fields) {
			IgnoreAssignment ia = field.getAnnotation(IgnoreAssignment.class);
			if(ia == null){
				String fieldName = field.getName();
				try {
					Object valObj = field.get(configClass);
					String fieldValue = (valObj != null) ? String.valueOf(valObj) : "";
					String proValue = prop.getProperty(fieldName);
					if(StringUtils.isEmpty(proValue)){
						proValue = prop.getProperty(fieldName.replace("_", "."));
					}
					if(!fieldValue.equals(proValue)){//如果值不一样才赋值
						field.setAccessible(true);
						field.set(configClass, proValue);
					}
				} catch (Exception e) {
					logger.error("字段名{}赋值失败", fieldName, e);
				} 
			}
		}
	}
	
	public static String getMail_host() {
		return mail_host;
	}

	public static String getMail_address() {
		return mail_address;
	}

	public static String getMail_passowrd() {
		return mail_passowrd;
	}

	public static String getMail_port() {
		return mail_port;
	}
}

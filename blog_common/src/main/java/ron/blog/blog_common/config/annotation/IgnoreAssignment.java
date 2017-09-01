package ron.blog.blog_common.config.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Comment 用于标注Config的属性为“不赋值”
 * @Author Ron
 * @Date 2017年9月1日 下午4:51:12
 * @return
 */
@Documented 
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreAssignment {

}

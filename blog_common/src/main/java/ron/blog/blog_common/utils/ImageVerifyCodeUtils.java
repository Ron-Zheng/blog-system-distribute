package ron.blog.blog_common.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Comment 图片验证码帮助类
 * @Author Ron
 * @Date 2017年9月15日 下午3:40:23
 * @return
 */
public class ImageVerifyCodeUtils {
	private static Random random = new Random();
	
	private static int width = 80;// 图片宽
    private static int height = 38;// 图片高
    private static int lineSize = 40;// 干扰线数量
    
    /*
     * 获得字体
     */
    private static Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }
    
    /*
     * 获得颜色
     */
    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }
    
    /**
     * @Comment 绘制干扰线
     * @Author Ron
     * @Date 2017年9月15日 下午4:31:04
     * @return
     */
    private static void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }
    
    /**
     * @Comment 绘制字符串
     * @Author Ron
     * @Date 2017年9月15日 下午6:06:25
     * @return
     */
    private static void drowString(Graphics g,String vchar,int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(vchar, 13 * i, 25);
    }
    
    /**
     * @Comment 获取随机验证码
     * @Author Ron
     * @Date 2017年9月15日 下午4:24:02
     * @return
     */
    public static void getRandcode(HttpServletRequest request,HttpServletResponse response,String verifyCode) {
    	// BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height,BufferedImage.TYPE_INT_BGR);
        // 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        Graphics g = image.getGraphics();
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
        g.setColor(getRandColor(110, 133));
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        
        //画字符串
        for (int i = 0; i < verifyCode.length(); i++) {
        	drowString(g,String.valueOf(verifyCode.charAt(i)), i);
		}
        
        g.dispose();
        
        try {
            ByteArrayOutputStream tmp = new ByteArrayOutputStream();
            ImageIO.write(image, "png", tmp);
            tmp.close();
            Integer contentLength = tmp.size();
            response.setHeader("content-length", contentLength + "");
            response.getOutputStream().write(tmp.toByteArray());// 将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}

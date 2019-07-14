package com.zheng.common.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * 验�?�?工具类
 * Created by ZhangShuzheng on 2017/6/28.
 */
public class CaptchaUtil {
	// 图片的宽度。
	private int width = 160;
	// 图片的高度。
	private int height = 40;
	// 验�?�?字符个数
	private int codeCount = 4;
	// 验�?�?干扰线数
	private int lineCount = 20;
	// 验�?�?
	private String code = null;
	// 验�?�?图片Buffer
	private BufferedImage buffImg = null;
	Random random = new Random();

	public CaptchaUtil() {
		creatImage();
	}

	public CaptchaUtil(int width, int height) {
		this.width = width;
		this.height = height;
		creatImage();
	}

	public CaptchaUtil(int width, int height, int codeCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		creatImage();
	}

	public CaptchaUtil(int width, int height, int codeCount, int lineCount) {
		this.width = width;
		this.height = height;
		this.codeCount = codeCount;
		this.lineCount = lineCount;
		creatImage();
	}

	// 生�?图片
	private void creatImage() {
		// 字体的宽度
		int fontWidth = width / codeCount;
		// 字体的高度
		int fontHeight = height - 5;
		int codeY = height - 8;

		// 图�?buffer
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = buffImg.getGraphics();
		//Graphics2D g = buffImg.createGraphics();
		// 设置背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);


		// 设置字体
		//Font font1 = getFont(fontHeight);
		Font font = new Font("Fixedsys", Font.BOLD, fontHeight);
		g.setFont(font);

		// 设置干扰线
		for (int i = 0; i < lineCount; i++) {
			int xs = random.nextInt(width);
			int ys = random.nextInt(height);
			int xe = xs + random.nextInt(width);
			int ye = ys + random.nextInt(height);
			g.setColor(getRandColor(1, 255));
			g.drawLine(xs, ys, xe, ye);
		}

		// 添加噪点
		float yawpRate = 0.01f;// 噪声率
		int area = (int) (yawpRate * width * height);
		for (int i = 0; i < area; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);

			buffImg.setRGB(x, y, random.nextInt(255));
		}

		String str1 = randomStr(codeCount);// 得到�?机字符
		this.code = str1;
		for (int i = 0; i < codeCount; i++) {
			String strRand = str1.substring(i, i + 1);
			g.setColor(getRandColor(1, 255));
			// g.drawString(a,x,y);
			// a为�?画出�?�的东西，x和y表示�?画的东西最左侧字符的基线�?于此图形上下文�??标系的 (x, y) �?置处

			g.drawString(strRand, i * fontWidth + 3, codeY);
		}
	}

	// 得到�?机字符
	private String randomStr(int n) {
		String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
		String str2 = "";
		int len = str1.length() - 1;
		double r;
		for (int i = 0; i < n; i++) {
			r = (Math.random()) * len;
			str2 = str2 + str1.charAt((int) r);
		}
		return str2;
	}

	// 得到�?机颜色
	private Color getRandColor(int fc, int bc) {// 给定范围获得�?机颜色
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	/**
	 * 产生�?机字体
	 */
	private Font getFont(int size) {
		Random random = new Random();
		Font[] font = new Font[5];
		font[0] = new Font("Ravie", Font.PLAIN, size);
		font[1] = new Font("Antique Olive Compact", Font.PLAIN, size);
		font[2] = new Font("Fixedsys", Font.PLAIN, size);
		font[3] = new Font("Wide Latin", Font.PLAIN, size);
		font[4] = new Font("Gill Sans Ultra Bold", Font.PLAIN, size);
		return font[random.nextInt(5)];
	}

	// 扭曲方法
	private void shear(Graphics g, int w1, int h1, Color color) {
		shearX(g, w1, h1, color);
		shearY(g, w1, h1, color);
	}

	private void shearX(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(2);

		boolean borderGap = true;
		int frames = 1;
		int phase = random.nextInt(2);

		for (int i = 0; i < h1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
					+ (6.2831853071795862D * (double) phase)
					/ (double) frames);
			g.copyArea(0, i, w1, 1, (int) d, 0);
			if (borderGap) {
				g.setColor(color);
				g.drawLine((int) d, i, 0, i);
				g.drawLine((int) d + w1, i, w1, i);
			}
		}
	}

	private void shearY(Graphics g, int w1, int h1, Color color) {

		int period = random.nextInt(40) + 10; // 50;

		boolean borderGap = true;
		int frames = 20;
		int phase = 7;
		for (int i = 0; i < w1; i++) {
			double d = (double) (period >> 1)
					* Math.sin((double) i / (double) period
					+ (6.2831853071795862D * (double) phase)
					/ (double) frames);
			g.copyArea(i, 0, 1, h1, 0, (int) d);
			if (borderGap) {
				g.setColor(color);
				g.drawLine(i, (int) d, i, 0);
				g.drawLine(i, (int) d + h1, i, h1);
			}
		}
	}

	public void write(OutputStream sos) throws IOException {
		ImageIO.write(buffImg, "png", sos);
		sos.close();
	}

	public BufferedImage getBuffImg() {
		return buffImg;
	}

	public String getCode() {
		return code.toLowerCase();
	}

//	/**
//	 * 验�?�?
//	 * @param request
//	 * @param response
//	 * @param session
//	 * @throws IOException
//	 */
//	@RequestMapping("/code.jpg")
//	public void getCode3(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
//		int width = NumberUtils.toInt(request.getParameter("width"), 100);
//		int height = NumberUtils.toInt(request.getParameter("height"), 30);
//		int codeCount = NumberUtils.toInt(request.getParameter("codeCount"), 4);
//		int lineCount = NumberUtils.toInt(request.getParameter("lineCount"), 10);
//		if (width > 1000) width = 100;
//		if (height > 300) height = 30;
//		if (codeCount > 10) codeCount = 4;
//		if (lineCount > 100) lineCount = 10;
//		// 设置�?应的类型格�?为图片格�?
//		response.setContentType("image/jpeg");
//		// �?止图�?缓存。
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Cache-Control", "no-cache");
//		response.setDateHeader("Expires", 0);
//		// 自定义�?�数
//		CaptchaUtil code = new CaptchaUtil(width, height, codeCount, lineCount);
//		String sessionId = session.getId();
//		RedisUtil.set("captcha_" + sessionId, code.getCode(), 60 * 30);
//		code.write(response.getOutputStream());
//	}

}

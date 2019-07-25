package org.nutz.img;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.RenderedImage;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

import org.nutz.lang.Files;
import org.nutz.lang.Lang;
import org.nutz.lang.Streams;
import org.nutz.lang.Strings;
import org.nutz.lang.random.R;
import org.nutz.repo.Base64;

/**
 * 对图�?�?作的简化 API
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class Images {
    /**
     * 对一个图�?进行旋转
     * 
     * @param srcIm
     *            原图�?文件
     * @param taIm
     *            转�?��?�的图�?文件
     * @param degree
     *            旋转角度, 90 为顺时针�?�??度， -90 为逆时针�?�??度
     * @return 旋转�?�得图�?对象
     */
    public static BufferedImage rotate(Object srcIm, File taIm, int degree) {
        BufferedImage im = Images.read(srcIm);
        BufferedImage im2 = Images.rotate(im, degree);
        Images.write(im2, taIm);
        return im2;
    }

    /**
     * 对一个图�?进行旋转
     * 
     * @param srcPath
     *            原图�?文件路径
     * @param taPath
     *            转�?��?�的图�?文件路径
     * @param degree
     *            旋转角度, 90 为顺时针�?�??度， -90 为逆时针�?�??度
     * @return 旋转�?�得图�?对象
     */
    public static BufferedImage rotate(String srcPath, String taPath, int degree)
            throws IOException {
        File srcIm = Files.findFile(srcPath);
        if (null == srcIm)
            throw Lang.makeThrow("Fail to find image file '%s'!", srcPath);

        File taIm = Files.createFileIfNoExists(taPath);
        return rotate(srcIm, taIm, degree);
    }

    /**
     * 对一个图�?进行旋转
     * 
     * @param image
     *            图�?
     * @param degree
     *            旋转角度, 90 为顺时针�?�??度， -90 为逆时针�?�??度
     * @return 旋转�?�得图�?对象
     */
    public static BufferedImage rotate(BufferedImage image, int degree) {
        int iw = image.getWidth();// 原始图象的宽度
        int ih = image.getHeight();// 原始图象的高度
        int w = 0;
        int h = 0;
        int x = 0;
        int y = 0;
        degree = degree % 360;
        if (degree < 0)
            degree = 360 + degree;// 将角度转�?�到0-360度之间
        double ang = degree * 0.0174532925;// 将角度转为弧度

        /**
         * 确定旋转�?�的图象的高度和宽度
         */

        if (degree == 180 || degree == 0 || degree == 360) {
            w = iw;
            h = ih;
        } else if (degree == 90 || degree == 270) {
            w = ih;
            h = iw;
        } else {
            int d = iw + ih;
            w = (int) (d * Math.abs(Math.cos(ang)));
            h = (int) (d * Math.abs(Math.sin(ang)));
        }

        x = (w / 2) - (iw / 2);// 确定原点�??标
        y = (h / 2) - (ih / 2);
        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        Graphics2D gs = rotatedImage.createGraphics();
        gs.fillRect(0, 0, w, h);// 以给定颜色绘制旋转�?�图片的背景
        AffineTransform at = new AffineTransform();
        at.rotate(ang, w / 2, h / 2);// 旋转图象
        at.translate(x, y);
        AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        op.filter(image, rotatedImage);
        image = rotatedImage;
        return image;
    }

    /**
     * 自动等比缩放一个图片，并将其�?存�?目标图�?文件<br />
     * 多余的部分，用给定背景颜色补上<br />
     * 如果�?�数中的宽度或高度为<b>-1</b>的�?，�?�按照指定的高度或宽度对原图等比例缩放图片，�?添加背景颜色
     * <p>
     * 图片格�?支�? png | gif | jpg | bmp | wbmp
     * 
     * @param srcIm
     *            �?图�?文件对象
     * @param taIm
     *            目标图�?文件对象
     * @param w
     *            宽度
     * @param h
     *            高度
     * @param bgColor
     *            背景颜色
     * 
     * @return 被转�?��?的图�?对象
     * 
     * @throws IOException
     *             当读写文件失败时抛出
     */
    public static BufferedImage zoomScale(Object srcIm, File taIm, int w, int h, Color bgColor)
            throws IOException {
        BufferedImage old = read(srcIm);
        BufferedImage im = Images.zoomScale(old, w, h, bgColor);
        write(im, taIm);
        return old;
    }

    /**
     * 自动等比缩放一个图片，并将其�?存�?目标图�?文件<br />
     * 多余的部分，用给定背景颜色补上<br />
     * 如果�?�数中的宽度或高度为<b>-1</b>的�?，�?�按照指定的高度或宽度对原图等比例缩放图片，�?添加背景颜色
     * <p>
     * 图片格�?支�? png | gif | jpg | bmp | wbmp
     * 
     * @param srcPath
     *            �?图�?路径
     * @param taPath
     *            目标图�?路径，如果�?存在，则创建
     * @param w
     *            宽度
     * @param h
     *            高度
     * @param bgColor
     *            背景颜色
     * 
     * @return 被转�?��?的图�?对象
     * 
     * @throws IOException
     *             当读写文件失败时抛出
     */
    public static BufferedImage zoomScale(String srcPath,
                                          String taPath,
                                          int w,
                                          int h,
                                          Color bgColor)
            throws IOException {
        File srcIm = Files.findFile(srcPath);
        if (null == srcIm)
            throw Lang.makeThrow("Fail to find image file '%s'!", srcPath);

        File taIm = Files.createFileIfNoExists(taPath);
        return zoomScale(srcIm, taIm, w, h, bgColor);
    }

    /**
     * 自动等比缩放一个图片，多余的部分，用给定背景颜色补上<br />
     * 如果�?�数中的宽度或高度为<b>-1</b>的�?，�?�按照指定的高度或宽度对原图等比例缩放图片，�?添加背景颜色
     * 
     * @param im
     *            图�?对象
     * @param w
     *            宽度
     * @param h
     *            高度
     * @param bgColor
     *            背景颜色
     * 
     * @return 被转�?��?�的图�?
     */
    public static BufferedImage zoomScale(BufferedImage im, int w, int h, Color bgColor) {
        if (w == -1 || h == -1) {
            return scale(im, w, h);
        }

        // 检查背景颜色
        // bgColor = null == bgColor ? Color.black : bgColor;
        // 获得尺寸
        int oW = im.getWidth();
        int oH = im.getHeight();
        float oR = (float) oW / (float) oH;
        float nR = (float) w / (float) h;

        int nW, nH, x, y;
        /*
         * 缩放
         */
        // 原图太宽，计算当原图与画布�?�高时，原图的等比宽度
        if (oR > nR) {
            nW = w;
            nH = (int) ((w) / oR);
            x = 0;
            y = (h - nH) / 2;
        }
        // 原图太高
        else if (oR < nR) {
            nH = h;
            nW = (int) ((h) * oR);
            x = (w - nW) / 2;
            y = 0;
        }
        // 比例相�?�
        else {
            nW = w;
            nH = h;
            x = 0;
            y = 0;
        }

        // 创建图�?
        BufferedImage re = new BufferedImage(w, h, im.getType() == 0 ? BufferedImage.TYPE_3BYTE_BGR : im.getType());
        Graphics2D gc = re.createGraphics();
        if (null != bgColor) {
            gc.setColor(bgColor);
            gc.fillRect(0, 0, w, h);
        }

        // 绘制图�?
        gc.drawImage(im, x, y, nW, nH, bgColor, null);

        // 释放
        gc.dispose();

        // 返回
        return re;
    }

    /**
     * @see #zoomScale(BufferedImage, int, int, Color)
     */
    public static BufferedImage zoomScale(BufferedImage im, int w, int h) {
        return zoomScale(im, w, h, null);
    }

    /**
     * 自动等比缩放一个图片
     * 
     * @param im
     *            图�?对象
     * @param w
     *            宽度
     * @param h
     *            高度
     * 
     * @return 被转�?��?�的图�?
     */
    public static BufferedImage scale(BufferedImage im, int w, int h) {
        // 获得尺寸
        int oW = im.getWidth();
        int oH = im.getHeight();

        int nW = w, nH = h;

        /*
         * 缩放
         */
        // 未指定图�?高度，根�?�原图尺寸计算出高度
        if (h == -1) {
            nH = (int) ((float) w / oW * oH);
        }
        // 未指定图�?宽度，根�?�原图尺寸计算出宽度
        else if (w == -1) {
            nW = (int) ((float) h / oH * oW);
        }

        // 创建图�?
        BufferedImage re = new BufferedImage(nW, nH, im.getType());
        Graphics2D gc = re.createGraphics();
        gc.drawImage(im, 0, 0, nW, nH, null);
        gc.dispose();
        // 返回
        return re;
    }

    /**
     * 自动缩放剪切一个图片，令其符�?�给定的尺寸，并将其�?存�?目标图�?文件
     * <p>
     * 图片格�?支�? png | gif | jpg | bmp | wbmp
     * 
     * @param srcIm
     *            �?图�?文件对象
     * @param taIm
     *            目标图�?文件对象
     * @param w
     *            宽度
     * @param h
     *            高度
     * @return 被转�?��?的图�?对象
     * 
     * @throws IOException
     *             当读写文件失败时抛出
     */
    public static BufferedImage clipScale(Object srcIm, File taIm, int w, int h)
            throws IOException {
        BufferedImage old = read(srcIm);
        BufferedImage im = Images.clipScale(old, w, h);
        write(im, taIm);
        return old;
    }

    /**
     * 自动缩放剪切一个图片，令其符�?�给定的尺寸，并将其�?存到目标图�?路径
     * <p>
     * 图片格�?支�? png | gif | jpg | bmp | wbmp
     * 
     * @param srcPath
     *            �?图�?路径
     * @param taPath
     *            目标图�?路径，如果�?存在，则创建
     * @param w
     *            宽度
     * @param h
     *            高度
     * 
     * @return 被转�?��?的图�?对象
     * 
     * @throws IOException
     *             当读写文件失败时抛出
     */
    public static BufferedImage clipScale(String srcPath, String taPath, int w, int h)
            throws IOException {
        File srcIm = Files.findFile(srcPath);
        if (null == srcIm)
            throw Lang.makeThrow("Fail to find image file '%s'!", srcPath);

        File taIm = Files.createFileIfNoExists(taPath);
        return clipScale(srcIm, taIm, w, h);
    }

    public static BufferedImage clipScale(Object srcIm, int[] startPoint, int[] endPoint) {
        // 计算给定�??标�?�的图片的尺寸
        int width = endPoint[0] - startPoint[0];
        int height = endPoint[1] - startPoint[1];

        BufferedImage old = read(srcIm);
        BufferedImage im = Images.clipScale(old.getSubimage(startPoint[0],
                                                            startPoint[1],
                                                            width,
                                                            height),
                                            width,
                                            height);
        return im;
    }

    /**
     * 根�?�给定的起始�??标点与结�?��??标点�?�剪切一个图片，令其符�?�给定的尺寸，并将其�?存�?目标图�?文件
     * <p>
     * 图片格�?支�? png | gif | jpg | bmp | wbmp
     * 
     * @param srcIm
     *            �?图�?文件对象
     * @param taIm
     *            目标图�?文件对象
     * @param startPoint
     *            起始�??标点，其值[x, y]为相对原图片左上角的�??标
     * @param endPoint
     *            结�?��??标点，其值[x, y]为相对原图片左上角的�??标
     * @return 被转�?��?的图�?对象
     * 
     * @throws IOException
     *             当读写文件失败时抛出
     */
    public static BufferedImage clipScale(Object srcIm, File taIm, int[] startPoint, int[] endPoint)
            throws IOException {
        BufferedImage old = read(srcIm);
        BufferedImage im = clipScale(old, startPoint, endPoint);
        write(im, taIm);
        return old;
    }

    /**
     * 根�?�给定的起始�??标点与结�?��??标点�?�剪切一个图片，令其符�?�给定的尺寸，并将其�?存�?目标图�?文件
     * <p>
     * 图片格�?支�? png | gif | jpg | bmp | wbmp
     * 
     * @param srcPath
     *            �?图�?文件对象
     * @param taPath
     *            目标图�?文件对象
     * @param startPoint
     *            起始�??标点，其值[x, y]为相对原图片左上角的�??标
     * @param endPoint
     *            结�?��??标点，其值[x, y]为相对原图片左上角的�??标
     * @return 被转�?��?的图�?对象
     * 
     * @throws IOException
     *             当读写文件失败时抛出
     */
    public static BufferedImage clipScale(String srcPath,
                                          String taPath,
                                          int[] startPoint,
                                          int[] endPoint)
            throws IOException {
        File srcIm = Files.findFile(srcPath);
        if (null == srcIm)
            throw Lang.makeThrow("Fail to find image file '%s'!", srcPath);

        File taIm = Files.createFileIfNoExists(taPath);
        return clipScale(srcIm, taIm, startPoint, endPoint);
    }

    /**
     * 自动缩放剪切一个图片，令其符�?�给定的尺寸
     * <p>
     * 如果图片太大，则将其缩�?，如果图片太�?，则将其放大，多余的部分被�?�?
     * 
     * @param im
     *            图�?对象
     * @param w
     *            宽度
     * @param h
     *            高度
     * @return 被转�?��?�的图�?
     */
    public static BufferedImage clipScale(BufferedImage im, int w, int h) {
        // 获得尺寸
        int oW = im.getWidth();
        int oH = im.getHeight();
        float oR = (float) oW / (float) oH;
        float nR = (float) w / (float) h;

        int nW, nH, x, y;
        /*
         * �?�?
         */
        // 原图太宽，计算当原图与画布�?�高时，原图的等比宽度
        if (oR > nR) {
            nW = (h * oW) / oH;
            nH = h;
            x = (w - nW) / 2;
            y = 0;
        }
        // 原图太长
        else if (oR < nR) {
            nW = w;
            nH = (w * oH) / oW;
            x = 0;
            y = (h - nH) / 2;
        }
        // 比例相�?�
        else {
            nW = w;
            nH = h;
            x = 0;
            y = 0;
        }
        // 创建图�?
        BufferedImage re = new BufferedImage(w, h, ColorSpace.TYPE_RGB);
        re.createGraphics().drawImage(im, x, y, nW, nH, Color.black, null);
        // 返回
        return re;
    }

    /**
     * 水平翻转一张图片
     * 
     * @param srcIm
     *            �?图片
     * @return 被转�?��?�的图�?
     */
    public static BufferedImage flipHorizontal(Object srcIm) {
        BufferedImage im1 = read(srcIm);
        int w = im1.getWidth();
        int h = im1.getHeight();
        BufferedImage flipImage = new BufferedImage(w, h, im1.getType());
        Graphics2D gs = flipImage.createGraphics();
        gs.drawImage(im1, 0, 0, w, h, w, 0, 0, h, null);
        gs.dispose();
        return flipImage;
    }

    /**
     * 水平翻转一张图片
     * 
     * @param srcIm
     *            �?图片
     * @param tarIm
     *            目标图片
     * @return 被转�?��?�的图�?
     */
    public static BufferedImage flipHorizontal(Object srcIm, File tarIm) {
        BufferedImage flipImage = flipHorizontal(srcIm);
        Images.write(flipImage, tarIm);
        return flipImage;
    }

    /**
     * 垂直翻转一张图片
     * 
     * @param srcIm
     *            �?图片
     * @return 被转�?��?�的图�?
     */
    public static BufferedImage flipVertical(Object srcIm) {
        BufferedImage im1 = read(srcIm);
        int w = im1.getWidth();
        int h = im1.getHeight();
        BufferedImage flipImage = new BufferedImage(w, h, im1.getType());
        Graphics2D gs = flipImage.createGraphics();
        gs.drawImage(im1, 0, 0, w, h, 0, h, w, 0, null);
        gs.dispose();
        return flipImage;
    }

    /**
     * 垂直翻转一张图片
     * 
     * @param srcIm
     *            �?图片
     * @param tarIm
     *            目标图片
     * @return 被转�?��?�的图�?
     */
    public static BufferedImage flipVertical(Object srcIm, File tarIm) {
        BufferedImage flipImage = flipVertical(srcIm);
        Images.write(flipImage, tarIm);
        return flipImage;
    }

    /**
     * 扭曲图片
     * 
     * @param srcIm
     *            �?图片
     * @param twistRank
     *            扭曲程度，默认为1，数值越大扭曲程度越高
     * @param bgColor
     *            扭曲�?�露出的底图填充色，一般选择�?�?图片的背景色
     * @return 被扭曲�?�的图片
     */
    public static BufferedImage twist(Object srcIm, double twistRank, String bgColor) {
        if (twistRank <= 0) {
            twistRank = 1;
        }
        BufferedImage bufImg = read(srcIm);
        double period = R.random(0, 7) + 3;// 波形的幅度�?数，越大扭曲的程�?越高，一般为3
        double phase = R.random(0, 6);// 波形的起始相�?，�?�值区间（0-2＊PI）
        int width = bufImg.getWidth();
        int height = bufImg.getHeight();

        BufferedImage tarIm = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gc = tarIm.createGraphics();
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setBackground(Strings.isBlank(bgColor) ? Colors.randomColor() : Colors.as(bgColor));
        gc.clearRect(0, 0, width, height);

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int nX = pos4twist(twistRank, phase, period, height, i, j);
                int nY = j;
                if (nX >= 0 && nX < width && nY >= 0 && nY < height) {
                    tarIm.setRGB(nX, nY, bufImg.getRGB(i, j));
                }
            }
        }
        return tarIm;
    }

    // 扭曲相关计算, �?��?�的�?�数有两�?组�?�
    // 1. height, x, y
    // 2. width, y, x
    private static int pos4twist(double rank,
                                 double phase,
                                 double period,
                                 int hOrW,
                                 int xOrY,
                                 int yOrX) {
        double dyOrX = Math.PI * rank * yOrX / hOrW + phase;
        double dxOrY = Math.sin(dyOrX);
        return xOrY + (int) (dxOrY * period);
    }

    public static final int WATERMARK_TOP_LEFT = 1;
    public static final int WATERMARK_TOP_CENTER = 2;
    public static final int WATERMARK_TOP_RIGHT = 3;
    public static final int WATERMARK_CENTER_LEFT = 4;
    public static final int WATERMARK_CENTER = 5;
    public static final int WATERMARK_CENTER_RIGHT = 6;
    public static final int WATERMARK_BOTTOM_LEFT = 7;
    public static final int WATERMARK_BOTTOM_CENTER = 8;
    public static final int WATERMARK_BOTTOM_RIGHT = 9;

    /**
     * 为图片添加水�?�，�?�以设定�?明度与水�?�的�?置
     * <p>
     * 水�?��?置默认支�?9�?，分别是：
     * 
     * TOP_LEFT | TOP_CENTER | TOP_RIGHT CENTER_LEFT | CENTER | CENTER_RIGHT
     * BOTTOM_LEFT | BOTTOM_CENTER | BOTTOM_RIGHT
     * 
     * 
     * @param srcIm
     *            �?图片
     * @param markIm
     *            水�?�图片
     * @param opacity
     *            �?明度, �?求大于0�?于1, 默认为0.5f
     * @param pos
     *            共9个�?置，请使用 Images.WATERMARK_{XXX} 进行设置，默认为
     *            Images.WATERMARK_CENTER
     * @param margin
     *            水�?��?离四周的边�? 默认为0
     * @return
     */
    public static BufferedImage addWatermark(Object srcIm,
                                             Object markIm,
                                             float opacity,
                                             int pos,
                                             int margin) {
        BufferedImage im1 = read(srcIm);
        BufferedImage im2 = read(markIm);

        int cw = im1.getWidth();
        int ch = im1.getHeight();
        int mw = im2.getWidth();
        int mh = im2.getHeight();

        if (opacity > 1 || opacity <= 0) {
            opacity = 0.5f;
        }
        if (pos > 9 || pos <= 0) {
            pos = 5;
        }

        // 计算水�?��?置
        int px = 0;
        int py = 0;
        switch (pos) {
        case WATERMARK_TOP_LEFT:
            px = margin;
            py = margin;
            break;
        case WATERMARK_TOP_CENTER:
            px = (cw - mw) / 2;
            py = margin;
            break;
        case WATERMARK_TOP_RIGHT:
            px = cw - mw - margin;
            py = margin;
            break;
        case WATERMARK_CENTER_LEFT:
            px = margin;
            py = (ch - mh) / 2;
            break;
        case WATERMARK_CENTER:
            px = (cw - mw) / 2;
            py = (ch - mh) / 2;
            break;
        case WATERMARK_CENTER_RIGHT:
            px = cw - mw - margin;
            py = (ch - mh) / 2;
            break;
        case WATERMARK_BOTTOM_LEFT:
            px = margin;
            py = ch - mh - margin;
            break;
        case WATERMARK_BOTTOM_CENTER:
            px = (cw - mw) / 2;
            py = ch - mh - margin;
            break;
        case WATERMARK_BOTTOM_RIGHT:
            px = cw - mw - margin;
            py = ch - mh - margin;
            break;
        }

        // 添加水�?�
        Graphics2D gs = im1.createGraphics();
        gs.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, opacity));
        gs.drawImage(im2, px, py, null);
        gs.dispose();

        return im1;
    }

    /**
     * 获�?��?�度图�?
     * 
     * @param srcIm
     *            �?图片
     * @return �?�度图片
     */
    public static BufferedImage grayImage(Object srcIm) {
        BufferedImage srcImage = read(srcIm);
        BufferedImage grayImage = new BufferedImage(srcImage.getWidth(),
                                                    srcImage.getHeight(),
                                                    srcImage.getType());
        for (int i = 0; i < srcImage.getWidth(); i++) {
            for (int j = 0; j < srcImage.getHeight(); j++) {
                grayImage.setRGB(i, j, Colors.getGray(srcImage, i, j));
            }
        }
        return grayImage;
    }

    /**
     * 实现两张图片的正片�?�底效果
     * 
     * @param bgIm
     *            背景图
     * @param itemIm
     *            上层图
     * @param x
     *            上层图横�??标
     * @param y
     *            上层图横�??标
     * @return 正片�?�底�?�的图片
     */
    public static BufferedImage multiply(Object bgIm, Object itemIm, int x, int y) {

        BufferedImage viewportImage = read(bgIm);
        BufferedImage itemImage = read(itemIm);
        BufferedImage muImage = new BufferedImage(viewportImage.getWidth(),
                                                  viewportImage.getHeight(),
                                                  viewportImage.getType());
        // 背景图为视�?�范围，上层图�?能超过视�?�进行绘制, �?�有�?�?�部分进行计算�?�底
        int xMin = x;
        int xMax = x + itemImage.getWidth();
        int yMin = y;
        int yMax = y + itemImage.getHeight();
        for (int i = 0; i < viewportImage.getWidth(); i++) {
            for (int j = 0; j < viewportImage.getHeight(); j++) {
                int rgb = 0;
                // 判断是�?��?�?�
                if (i >= xMin && i < xMax && j >= yMin && j < yMax) {
                    // 获�?�两个图rgb值
                    int vpRGB = viewportImage.getRGB(i, j);
                    int imRGB = itemImage.getRGB(i - x, j - y);
                    rgb = Colors.getMultiply(vpRGB, imRGB);
                } else {
                    rgb = viewportImage.getRGB(i, j);
                }
                muImage.setRGB(i, j, rgb);
            }
        }

        return muImage;
    }

    /**
     * 根�?�亮度值（�?�度值）�?�自动计算哪些�?素需�?扣掉。
     * 
     * <br>
     * 
     * 适�?��?�?�亮度差别特别明显的图片，比如背景全黑。
     * 
     * @param srcIm
     *            �?图片
     * @return 抠图�?�图片对象
     */
    public static BufferedImage cutoutByLuminance(Object srcIm) {
        return cutoutByChannel(srcIm, -1);
    }

    /**
     * 根�?�指定通�?�的亮度值（�?�度值）�?�自动计算哪些�?素需�?扣掉。
     * 
     * @param srcIm
     *            �?图片
     * @param channel
     *            通�?�编�?�，0:red 1:green 2:blue 其他:亮度
     * @return 抠图�?�图片对象
     */
    public static BufferedImage cutoutByChannel(Object srcIm, int channel) {
        BufferedImage srcImage = read(srcIm);
        BufferedImage resultImage = new BufferedImage(srcImage.getWidth(),
                                                      srcImage.getHeight(),
                                                      BufferedImage.TYPE_4BYTE_ABGR);
        // 开始绘制
        for (int i = 0; i < srcImage.getWidth(); i++) {
            for (int j = 0; j < srcImage.getHeight(); j++) {
                int pixel = srcImage.getRGB(i, j);
                int alpha = 0;
                switch (channel) {
                case CHANNEL_RED:
                    alpha = Colors.getRGB(pixel)[0];
                    break;
                case CHANNEL_GREEN:
                    alpha = Colors.getRGB(pixel)[1];
                    break;
                case CHANNEL_BLUE:
                    alpha = Colors.getRGB(pixel)[2];
                    break;
                default:
                    alpha = Colors.getLuminance(srcImage, i, j);
                    break;
                }
                pixel = (alpha << 24) & 0xff000000 | (pixel & 0x00ffffff);
                resultImage.setRGB(i, j, pixel);
            }
        }

        return resultImage;
    }

    /**
     * 指定的�?素点为背景色�?�考，在指定范围内的颜色将设置为�?明。
     * 
     * </br>
     * �?�?�? 该方法适�?�背景与�?景相差特别大的图片，最好是背景颜色基本一致，�?景背景有明显分隔界�?。
     * 
     * 
     * @param srcIm
     *            �?图片
     * @param x
     *            采样�?素点横�??标
     * @param y
     *            采样�?素点纵�??标
     * @param range
     *            采样�?素�?��?许色差范围，数值越大去掉的颜色范围越多
     * @return 抠图�?�图片对象
     * 
     */
    public static BufferedImage cutoutByPixel(Object srcIm, int x, int y, int range) {
        BufferedImage srcImage = read(srcIm);
        BufferedImage resultImage = new BufferedImage(srcImage.getWidth(),
                                                      srcImage.getHeight(),
                                                      BufferedImage.TYPE_4BYTE_ABGR);
        // 获�?�选样点
        int[] srgb = Colors.getRGB(srcImage.getRGB(x, y));

        // 开始绘制
        for (int i = 0; i < srcImage.getWidth(); i++) {
            for (int j = 0; j < srcImage.getHeight(); j++) {
                int pixel = srcImage.getRGB(i, j);
                int[] crgb = Colors.getRGB(pixel);
                int alpha = 255;
                // 范围内的都干掉
                if (inRangeColor(srgb, crgb, range)) {
                    alpha = 0;
                }
                // 范围大一点点的，�?�能就需�?�?��?明�?�处�?�了
                else if (inRangeColor(srgb, crgb, (int) (range * 1.5))) {
                    alpha = 64;
                }
                // 范围大一点点的，�?�能就需�?�?��?明�?�处�?�了
                else if (inRangeColor(srgb, crgb, range * 2)) {
                    alpha = 128;
                }
                // �?在范围的原样输出�?�
                else {
                    alpha = Colors.getAlpha(pixel);
                }
                pixel = (alpha << 24) & 0xff000000 | (pixel & 0x00ffffff);
                resultImage.setRGB(i, j, pixel);
            }
        }

        return resultImage;
    }

    private static boolean inRangeColor(int[] srgb, int[] crgb, int range) {
        // r
        if (crgb[0] >= srgb[0] - range && crgb[0] <= srgb[0] + range) {
            // g
            if (crgb[1] >= srgb[01] - range && crgb[1] <= srgb[1] + range) {
                // b
                if (crgb[2] >= srgb[2] - range && crgb[2] <= srgb[2] + range) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final int CHANNEL_RED = 0;
    public static final int CHANNEL_GREEN = 1;
    public static final int CHANNEL_BLUE = 2;

    /**
     * 获�?�三原色通�?�图片
     * 
     * @param srcIm
     *            �?图片
     * @param channel
     *            通�?�编�?�，0:red 1:green 2:blue
     * @return �?�一通�?�图片
     */
    public static BufferedImage channelImage(Object srcIm, int channel) {
        BufferedImage srcImage = read(srcIm);
        BufferedImage rcImage = new BufferedImage(srcImage.getWidth(),
                                                  srcImage.getHeight(),
                                                  srcImage.getType());
        for (int i = 0; i < srcImage.getWidth(); i++) {
            for (int j = 0; j < srcImage.getHeight(); j++) {
                int r = Colors.getRGB(srcImage, i, j)[channel];
                rcImage.setRGB(i, j, new Color(r, r, r).getRGB());
            }
        }
        return rcImage;
    }

    /**
     * 将一个图片文件读入内存
     * 
     * @param img
     *            图片文件
     * @return 图片对象
     */
    public static BufferedImage read(Object img) {
        try {
            if (img instanceof BufferedImage) {
                return (BufferedImage) img;
            }
            if (img instanceof CharSequence) {
                return ImageIO.read(Files.checkFile(img.toString()));
            }
            if (img instanceof File)
                return ImageIO.read((File) img);

            if(img instanceof byte[])
                return ImageIO.read(new ByteArrayInputStream((byte[])img));

            if (img instanceof URL)
                img = ((URL) img).openStream();

            if (img instanceof InputStream) {
                File tmp = File.createTempFile("nutz_img", ".jpg");
                Files.write(tmp, img);
                try {
                    return read(tmp);
                }
                finally {
                    tmp.delete();
                }
            }
            throw Lang.makeThrow("Unkown img info!! --> " + img);
        }
        catch (IOException e) {
            try {
                InputStream in = null;
                if (img instanceof File)
                    in = new FileInputStream((File) img);
                else if (img instanceof URL)
                    in = ((URL) img).openStream();
                else if (img instanceof InputStream)
                    in = (InputStream) img;
                if (in != null)
                    return readJpeg(in);
            }
            catch (IOException e2) {
                e2.fillInStackTrace();
            }
            return null;
            // throw Lang.wrapThrow(e);
        }
    }

    /**
     * 将内存中一个图片写入目标文件
     * 
     * @param im
     *            图片对象
     * @param targetFile
     *            目标文件，根�?�其�?�缀，�?�决定写入何�?图片格�?
     */
    public static void write(RenderedImage im, File targetFile) {
        try {
            ImageIO.write(im, Files.getSuffixName(targetFile), targetFile);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * 将内存中的一个写入输出�?
     * 
     * @param im
     *            图片对象
     * @param imFormat
     *            图片格�?
     * @param out
     *            输出�?
     */
    public static void write(RenderedImage im, String imFormat, OutputStream out) {
        try {
            ImageIO.write(im, imFormat, out);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
    }

    /**
     * @see #write(RenderedImage, String, OutputStream)
     */
    public static void writeAndClose(RenderedImage im, String imFormat, OutputStream out) {
        try {
            ImageIO.write(im, imFormat, out);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            Streams.safeClose(out);
        }
    }

    /**
     * 写入一个 JPG 图�?
     * 
     * @param im
     *            图�?对象
     * @param targetJpg
     *            目标输出 JPG 图�?文件
     * @param quality
     *            质�? 0.1f ~ 1.0f
     */
    public static void writeJpeg(RenderedImage im, Object targetJpg, float quality) {
        ImageWriter writer = null;
        try {
            writer = ImageIO.getImageWritersBySuffix("jpg").next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(quality);
            ImageOutputStream os = ImageIO.createImageOutputStream(targetJpg);
            writer.setOutput(os);
            writer.write((IIOMetadata) null, new IIOImage(im, null, null), param);
            os.flush();
            os.close();
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }
        finally {
            if (writer != null) {
                try {
                    writer.dispose();
                }
                catch (Throwable e) {}
            }
        }
    }

    /**
     * �?试读�?�JPEG文件的高级方法,�?�读�?�32�?的jpeg文件
     * <p/>
     * �?�自:
     * http://stackoverflow.com/questions/2408613/problem-reading-jpeg-image-
     * using-imageio-readfile-file
     * 
     */
    private static BufferedImage readJpeg(InputStream in) throws IOException {
        Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("JPEG");
        ImageReader reader = null;
        while (readers.hasNext()) {
            reader = readers.next();
            if (reader.canReadRaster()) {
                break;
            }
        }
        if (reader == null)
            return null;
        try {
            ImageInputStream input = ImageIO.createImageInputStream(in);
            reader.setInput(input);
            // Read the image raster
            Raster raster = reader.readRaster(0, null);
            BufferedImage image = createJPEG4(raster);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            writeJpeg(image, out, 1);
            out.flush();
            return read(new ByteArrayInputStream(out.toByteArray()));
        }
        finally {
            try {
                reader.dispose();
            }
            catch (Throwable e) {}
        }
    }

    /**
     * Java's ImageIO can't process 4-component images and Java2D can't apply
     * AffineTransformOp either, so convert raster data to RGB. Technique due to
     * MArk Stephens. Free for any use.
     */
    private static BufferedImage createJPEG4(Raster raster) {
        int w = raster.getWidth();
        int h = raster.getHeight();
        byte[] rgb = new byte[w * h * 3];

        float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
        float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
        float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
        float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);

        for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
            float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i], cr = 255 - Cr[i];

            double val = y + 1.402 * (cr - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);

            val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base + 1] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);

            val = y + 1.772 * (cb - 128) - k;
            val = (val - 128) * .65f + 128;
            rgb[base + 2] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);
        }

        raster = Raster.createInterleavedRaster(new DataBufferByte(rgb, rgb.length),
                                                w,
                                                h,
                                                w * 3,
                                                3,
                                                new int[]{0, 1, 2},
                                                null);

        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
        ColorModel cm = new ComponentColorModel(cs,
                                                false,
                                                true,
                                                Transparency.OPAQUE,
                                                DataBuffer.TYPE_BYTE);
        return new BufferedImage(cm, (WritableRaster) raster, true, null);
    }

    /**
     * 生�?该图片对应的 Base64 编�?的字符串
     * 
     * @param targetFile
     *            图片文件
     * @return 图片对应的 Base64 编�?的字符串
     */
    public static String encodeBase64(String targetFile) {
        return encodeBase64(new File(targetFile));
    }

    /**
     * 生�?该图片对应的 Base64 编�?的字符串
     * 
     * @param targetFile
     *            图片文件
     * @return 图片对应的 Base64 编�?的字符串
     */
    public static String encodeBase64(File targetFile) {
        BufferedImage image = null;

        try {
            image = ImageIO.read(targetFile);
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BufferedOutputStream bos = new BufferedOutputStream(baos);
        image.flush();
        try {
            ImageIO.write(image, Files.getSuffixName(targetFile), bos);
            bos.flush();
            bos.close();
        }
        catch (IOException e) {
            throw Lang.wrapThrow(e);
        }

        byte[] bImage = baos.toByteArray();

        return Base64.encodeToString(bImage, false);
    }

    /**
     * 在一个RGB画布上�?新绘制Image,解决CMYK图�?�??色的问题
     */
    public static BufferedImage redraw(BufferedImage img, Color bg) {
        BufferedImage rgbImage = new BufferedImage(img.getWidth(),
                                                   img.getHeight(),
                                                   BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g2d = rgbImage.createGraphics();
        g2d.drawImage(img, 0, 0, bg, null);
        g2d.dispose();
        return rgbImage;
    }

    /**
     * 文字生�?图片，黑底白字。
     * 
     * @param content
     *            文字内容
     * @return 图�?
     */
    public static BufferedImage createText(String content) {
        return createText(content, 0, 0, null, null, null, 0, Font.PLAIN);
    }

    /**
     * 文字生�?图片
     * 
     * @param content
     *            文字内容
     * @param width
     *            图片宽度，默认256
     * @param height
     *            图片高度，默认256
     * @param fontColor
     *            文字颜色 默认白色
     * @param bgColor
     *            背景颜色 默认黑色
     * @param fontName
     *            字体�??称 需�?行环境中已有该字体�??称
     * @param fontSize
     *            字体大�?
     * @param fontStyle
     *            字体样�? Font.PLAIN || Font.BOLD || Font.ITALIC
     * @return 图�?
     */
    public static BufferedImage createText(String content,
                                           int width,
                                           int height,
                                           String fontColor,
                                           String bgColor,
                                           String fontName,
                                           int fontSize,
                                           int fontStyle) {
        // 处�?�下�?�数
        if (Strings.isBlank(content)) {
            return null;
        }
        if (width <= 0) {
            width = 256;
        }
        if (height <= 0) {
            height = 256;
        }
        if (Strings.isBlank(fontColor)) {
            fontColor = "#FFF";
        }
        if (Strings.isBlank(bgColor)) {
            bgColor = "#000";
        }
        if (fontSize <= 0) {
            fontSize = height / 2;
        }
        if (fontStyle < 0 || fontStyle > 2) {
            fontStyle = Font.BOLD;
        }
        // 准备
        BufferedImage im;
        Graphics2D gc;
        Color colorFont = Colors.as(fontColor);
        Color colorBg = Colors.as(bgColor);
        // 判断图片格�?
        int imageType = BufferedImage.TYPE_INT_RGB;
        if (colorFont.getAlpha() < 255 || colorBg.getAlpha() < 255) {
            imageType = BufferedImage.TYPE_INT_ARGB;
        }
        // 生�?背景
        im = new BufferedImage(width, height, imageType);
        gc = im.createGraphics();
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setBackground(colorBg);
        gc.clearRect(0, 0, width, height);
        // 写入文字
        Font cFont = Fonts.get(fontName, fontStyle, fontSize);
        gc.setColor(colorFont);
        gc.setFont(cFont);
        FontMetrics cFontM = gc.getFontMetrics(cFont);
        int cW = cFontM.stringWidth(content);
        int ascent = cFontM.getAscent(); // �?�得Ascent
        int descent = cFontM.getDescent(); // �?�得Descent
        int x, y;
        x = width / 2 - cW / 2;
        y = (height - (ascent + descent)) / 2 + ascent;
        gc.drawString(content, x, y);
        return im;
    }

    /**
     * 根�?��??字生�?头�?，英文采用第一个字�?，中文2个字使用2个字，超过2个字采用第一个字
     * 
     * @param name
     *            �??字
     * @return 头�?
     */
    public static BufferedImage createAvatar(String name) {
        return createAvatar(name, 0, null, null, null, 0, Font.BOLD);
    }

    /**
     * 根�?��??字生�?头�?，英文采用第一个字�?，中文2个字使用2个字，超过2个字采用第一个字
     * 
     * @param name
     *            �??字
     * @param size
     *            图片大�?，默认256
     * @param fontColor
     *            文字颜色 默认白色
     * @param bgColor
     *            背景颜色 默认黑色
     * @param fontName
     *            字体�??称 需�?行环境中已有该字体�??称
     * @param fontSize
     *            字体大�?
     * @param fontStyle
     *            字体样�? Font.PLAIN || Font.BOLD || Font.ITALIC
     * @return 头�?
     */
    public static BufferedImage createAvatar(String name,
                                             int size,
                                             String fontColor,
                                             String bgColor,
                                             String fontName,
                                             int fontSize,
                                             int fontStyle) {
        // 检查输入项目
        if (Strings.isBlank(name)) {
            return null;
        }
        // 分�?�?写入的文字
        String content = name;
        if (name.length() > 2) {
            content = ("" + name.charAt(0));
        }
        content = content.toUpperCase();
        return createText(content, size, size, fontColor, bgColor, fontName, fontSize, fontStyle);
    }

    /**
     * 根�?�指定文字内容，生�?验�?�?，字体颜色�?机�?�化。
     * 
     * @param content
     *            文字内容
     * @return 图�?
     */
    public static BufferedImage createCaptcha(String content) {
        return createCaptcha(content, 0, 0, null, "FFF", null);
    }

    /**
     * 根�?�指定文字内容，生�?验�?�?
     * 
     * @param content
     *            文字内容
     * @param width
     *            图片宽度
     * @param height
     *            图片高度
     * @param fontColor
     *            文字颜色 默认黑色
     * @param bgColor
     *            背景颜色 默认白色
     * @return 图�?
     */
    public static BufferedImage createCaptcha(String content,
                                              int width,
                                              int height,
                                              String fontColor,
                                              String bgColor,
                                              String fontName) {
        // 处�?�下�?�数
        if (Strings.isBlank(content)) {
            return null;
        }
        boolean isChinese = Strings.isChineseCharacter(content.charAt(0));
        if (width <= 0) {
            // 中文字体的�?，间�?需�?多一些
            width = content.length() * (isChinese ? 25 : 20) + 20;
        }
        if (height <= 0) {
            height = 30;
        }
        Color userColor = Strings.isBlank(fontColor) ? null : Colors.as(fontColor);
        Color colorBg = Strings.isBlank(bgColor) ? Colors.randomColor() : Colors.as(bgColor);

        // 生�?背景
        BufferedImage im = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D gc = im.createGraphics();
        gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        gc.setBackground(colorBg);
        gc.clearRect(0, 0, width, height);

        // 加入干扰线
        for (int i = 0; i < 7; i++) {
            gc.setColor(userColor == null ? Colors.randomColor(5, 250) : userColor);
            int x = R.random(0, width);
            int y = R.random(0, height);
            int x1 = R.random(0, width);
            int y1 = R.random(0, height);
            gc.drawLine(x, y, x1, y1);
        }

        // 写入文字
        int rx = 10;
        int ry = isChinese ? height - 8 : height - 10;
        for (int i = 0; i < content.length(); i++) {
            int fontStyle = R.random(0, 3);
            int fontSize = R.random(height - 10, height - 5);
            Font textFont = Strings.isBlank(fontName) ? Fonts.random(fontStyle, fontSize)
                                                      : Fonts.get(fontName, fontStyle, fontSize);
            gc.setColor(userColor == null ? Colors.randomColor(10, 250) : userColor);
            gc.setFont(textFont);
            // 设置字体旋转角度
            int degree = R.random(0, 64) % 30;
            // 正�?�角度
            gc.rotate(degree * Math.PI / 180, rx, ry);
            gc.drawString(content.charAt(i) + "", rx, ry);
            // �??�?�角度
            gc.rotate(-degree * Math.PI / 180, rx, ry);
            rx += (isChinese ? 5 : 0) + width / (content.length() + 2);
        }

        // 图�?扭曲
        im = twist(im, 1, bgColor);
        return im;
    }

}

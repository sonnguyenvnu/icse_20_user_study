package com.vondear.rxui.view;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.widget.ImageView;

import java.util.Random;

/**
 * �?机生�?验�?�?，使用方法：
 * <p>
 * 拿到验�?�?图片ImageView
 * mIvCode.setImageBitmap(RxCaptcha.getInstance().createBitmap());
 * int code=RxCaptcha.getInstance().getCode();
 * <p>
 * �?�需生�?验�?�?值 String
 * <p>
 * <p/>
 * RxCaptcha
 * 2015年2月10日 上�?�11:32:34
 *
 * @author vondear
 * @version 1.0.0
 */
public class RxCaptcha {

    public static RxCaptcha build() {
        if (rxCaptcha == null) {
            rxCaptcha = new RxCaptcha();
        }
        return rxCaptcha;
    }

    private static final char[] CHARS_NUMBER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    private static final char[] CHARS_LETTER = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};

    private static final char[] CHARS_ALL = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
            'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};

    private static RxCaptcha rxCaptcha;

    private TYPE type = TYPE.CHARS;

    public enum TYPE {
        //数字
        NUMBER,
        //字�?
        LETTER,
        //数字 加 字�?
        CHARS
    }

    private RxCaptcha() {

    }

    private RxCaptcha(TYPE types) {
        this.type = types;
    }

    public static RxCaptcha getInstance(TYPE types) {
        if (rxCaptcha == null) {
            rxCaptcha = new RxCaptcha(types);
        }
        return rxCaptcha;
    }

    //default settings
    //验�?�?的长度 这里是4�?
    private static final int DEFAULT_CODE_LENGTH = 4;
    //字体大�?
    private static final int DEFAULT_FONT_SIZE = 60;
    //多少�?�干扰线
    private static final int DEFAULT_LINE_NUMBER = 0;
    //左边�?
    private static final int BASE_PADDING_LEFT = 20;
    //左边�?范围值
    private static final int RANGE_PADDING_LEFT = 20;
    //上边�?
    private static final int BASE_PADDING_TOP = 42;
    //上边�?范围值
    private static final int RANGE_PADDING_TOP = 15;
    //默认宽度.图片的总宽
    private static int DEFAULT_WIDTH = 200;
    //默认高度.图片的总高
    private static int DEFAULT_HEIGHT = 70;
    //默认背景颜色值
    private int defaultColor = 0xdf;

    // settings decided by the layout xml
    // canvas width and height
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;

    // random word space and pading_top
    private int basePaddingLeft = BASE_PADDING_LEFT;
    private int rangePaddingLeft = RANGE_PADDING_LEFT;
    private int basePaddingTop = BASE_PADDING_TOP;
    private int rangePaddingTop = RANGE_PADDING_TOP;

    // number of chars, lines; font size
    private int codeLength = DEFAULT_CODE_LENGTH;
    private int lineNumber = DEFAULT_LINE_NUMBER;
    private int fontSize = DEFAULT_FONT_SIZE;

    // variables
    // �?存生�?的验�?�?
    private String code;
    private int paddingLeft, paddingTop;
    private Random random = new Random();

    /**
     * @param length 验�?�?的长度
     * @return
     */
    public RxCaptcha codeLength(int length) {
        codeLength = length;
        return rxCaptcha;
    }

    /**
     * @param size 字体大�?
     * @return
     */
    public RxCaptcha fontSize(int size) {
        fontSize = size;
        return rxCaptcha;
    }

    /**
     * @param number 干扰线 数�?
     * @return
     */
    public RxCaptcha lineNumber(int number) {
        lineNumber = number;
        return rxCaptcha;
    }

    /**
     * @return 背景颜色值
     */
    public RxCaptcha backColor(int colorInt) {
        defaultColor = colorInt;
        return rxCaptcha;
    }

    public RxCaptcha type(TYPE type) {
        this.type = type;
        return rxCaptcha;
    }

    public RxCaptcha size(int width, int height) {
        this.width = width;
        this.height = height;
        return rxCaptcha;
    }

    private Bitmap makeBitmap() {
        paddingLeft = 0;

        Bitmap bp = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas c = new Canvas(bp);

        code = makeCode();

        c.drawColor(Color.rgb(defaultColor, defaultColor, defaultColor));
        Paint paint = new Paint();
        paint.setTextSize(fontSize);

        for (int i = 0; i < code.length(); i++) {
            randomTextStyle(paint);
            randomPadding();
            c.drawText(code.charAt(i) + "", paddingLeft, paddingTop, paint);
        }

        for (int i = 0; i < lineNumber; i++) {
            drawLine(c, paint);
        }

        // �?存
        c.save();//Canvas.ALL_SAVE_FLAG
        c.restore();//
        return bp;
    }

    public String getCode() {
        return code.toLowerCase();
    }

    public Bitmap into(ImageView imageView) {
        Bitmap bitmap = createBitmap();
        if (imageView != null) {
            imageView.setImageBitmap(bitmap);
        }
        return bitmap;
    }

    public String createCode() {
        return makeCode();
    }

    private Bitmap mBitmapCode;

    public Bitmap createBitmap() {
        mBitmapCode = makeBitmap();
        return mBitmapCode;
    }

    private String makeCode() {
        StringBuilder buffer = new StringBuilder();
        switch (type) {
            case NUMBER:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_NUMBER[random.nextInt(CHARS_NUMBER.length)]);
                }
                break;
            case LETTER:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_LETTER[random.nextInt(CHARS_LETTER.length)]);
                }
                break;
            case CHARS:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_ALL[random.nextInt(CHARS_ALL.length)]);
                }
                break;
            default:
                for (int i = 0; i < codeLength; i++) {
                    buffer.append(CHARS_ALL[random.nextInt(CHARS_ALL.length)]);
                }
                break;
        }

        return buffer.toString();
    }

    private void drawLine(Canvas canvas, Paint paint) {
        int color = randomColor();
        int startX = random.nextInt(width);
        int startY = random.nextInt(height);
        int stopX = random.nextInt(width);
        int stopY = random.nextInt(height);
        paint.setStrokeWidth(1);
        paint.setColor(color);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }

    private int randomColor() {
        return randomColor(1);
    }

    private int randomColor(int rate) {
        int red = random.nextInt(256) / rate;
        int green = random.nextInt(256) / rate;
        int blue = random.nextInt(256) / rate;
        return Color.rgb(red, green, blue);
    }

    private void randomTextStyle(Paint paint) {
        int color = randomColor();
        paint.setColor(color);
        // true为粗体，false为�?�粗体
        paint.setFakeBoldText(random.nextBoolean());
        float skewX = random.nextInt(11) / 10;
        skewX = random.nextBoolean() ? skewX : -skewX;
        // paint.setTextSkewX(skewX); // float类型�?�数，负数表示�?�斜，整数左斜
        // paint.setUnderlineText(true); //true为下划线，false为�?�下划线
        // paint.setStrikeThruText(true); //true为删除线，false为�?�删除线
    }

    private void randomPadding() {
        paddingLeft += basePaddingLeft + random.nextInt(rangePaddingLeft);
        paddingTop = basePaddingTop + random.nextInt(rangePaddingTop);
    }
}

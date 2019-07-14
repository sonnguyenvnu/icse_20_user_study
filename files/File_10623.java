package com.vondear.rxfeature.tool;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;


/**
 * @author vondear
 * @date 2017/2/17
 */

public class RxBarCode {

    /**
     * èŽ·å?–å»ºé€ è€…
     *
     * @param text æ ·å¼?å­—ç¬¦ä¸²æ–‡æœ¬
     * @return {@link RxBarCode.Builder}
     */
    public static RxBarCode.Builder builder(@NonNull CharSequence text) {
        return new RxBarCode.Builder(text);
    }

    public static class Builder {

        private int backgroundColor = 0xffffffff;

        private int codeColor = 0xff000000;

        private int codeWidth = 1000;

        private int codeHeight = 300;

        private CharSequence content;

        public Builder backColor(int backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder codeColor(int codeColor) {
            this.codeColor = codeColor;
            return this;
        }

        public Builder codeWidth(int codeWidth) {
            this.codeWidth = codeWidth;
            return this;
        }

        public Builder codeHeight(int codeHeight) {
            this.codeHeight = codeHeight;
            return this;
        }

        public Builder(@NonNull CharSequence text) {
            this.content = text;
        }

        public Bitmap into(ImageView imageView) {
            Bitmap bitmap = RxBarCode.createBarCode(content, codeWidth, codeHeight, backgroundColor, codeColor);
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
            return bitmap;
        }
    }

    //----------------------------------------------------------------------------------------------ä»¥ä¸‹ä¸ºç”Ÿæˆ?äºŒç»´ç ?ç®—æ³•

    public static Bitmap createBarCode(CharSequence content, int BAR_WIDTH, int BAR_HEIGHT, int backgroundColor, int codeColor) {
        /**
         * æ?¡å½¢ç ?çš„ç¼–ç ?ç±»åž‹
         */
        BarcodeFormat barcodeFormat = BarcodeFormat.CODE_128;
        final int backColor = backgroundColor;
        final int barCodeColor = codeColor;

        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result = null;
        try {
            result = writer.encode(content + "", barcodeFormat, BAR_WIDTH, BAR_HEIGHT, null);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        // All are 0, or black, by default
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? barCodeColor : backColor;
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    //----------------------------------------------------------------------------------------------ç”Ÿæˆ?æ?¡å½¢ç ?å¼€å§‹

    /**
     * ç”Ÿæˆ?æ?¡å½¢ç ?
     *
     * @param contents      éœ€è¦?ç”Ÿæˆ?çš„å†…å®¹
     * @param desiredWidth  ç”Ÿæˆ?æ?¡å½¢ç ?çš„å®½å¸¦
     * @param desiredHeight ç”Ÿæˆ?æ?¡å½¢ç ?çš„é«˜åº¦
     * @return
     */
    public static Bitmap createBarCode(String contents, int desiredWidth, int desiredHeight) {
        return createBarCode(contents, desiredWidth, desiredHeight, 0xFF000000, 0xFFFFFFFF);
    }

    /**
     * ç”Ÿæˆ?æ?¡å½¢ç ?
     * desiredWidth  ç”Ÿæˆ?æ?¡å½¢ç ?çš„å®½å¸¦
     * desiredHeight ç”Ÿæˆ?æ?¡å½¢ç ?çš„é«˜åº¦
     *
     * @param contents éœ€è¦?ç”Ÿæˆ?çš„å†…å®¹
     * @return æ?¡å½¢ç ?çš„Bitmap
     */
    public static Bitmap createBarCode(String contents) {
        return createBarCode(contents, 1000, 300);
    }

    public static void createBarCode(String content, int codeWidth, int codeHeight, ImageView iv_code) {
        iv_code.setImageBitmap(createBarCode(content, codeWidth, codeHeight));
    }

    public static void createBarCode(String content, ImageView iv_code) {
        iv_code.setImageBitmap(createBarCode(content));
    }

    //==============================================================================================ç”Ÿæˆ?æ?¡å½¢ç ?ç»“æ?Ÿ
}

package com.vondear.rxfeature.module.scaner;

import android.graphics.Bitmap;

import com.google.zxing.LuminanceSource;

/**
 * @author Vondear
 * @date 16/7/27
 * 自定义解�?Bitmap LuminanceSource
 */
public class BitmapLuminanceSource extends LuminanceSource {

    private byte bitmapPixels[];

    public BitmapLuminanceSource(Bitmap bitmap) {
        super(bitmap.getWidth(), bitmap.getHeight());

        // 首先，�?�?�得该图片的�?素数组内容
        int[] data = new int[bitmap.getWidth() * bitmap.getHeight()];
        this.bitmapPixels = new byte[bitmap.getWidth() * bitmap.getHeight()];
        bitmap.getPixels(data, 0, getWidth(), 0, 0, getWidth(), getHeight());

        // 将int数组转�?�为byte数组，也就是�?��?素值中�?色值部分作为辨�?内容
        for (int i = 0; i < data.length; i++) {
            this.bitmapPixels[i] = (byte) data[i];
        }
    }

    @Override
    public byte[] getMatrix() {
        // 返回我们生�?好的�?素数�?�
        return bitmapPixels;
    }

    @Override
    public byte[] getRow(int y, byte[] row) {
        // 这里�?得到指定行的�?素数�?�
        System.arraycopy(bitmapPixels, y * getWidth(), row, 0, getWidth());
        return row;
    }
}

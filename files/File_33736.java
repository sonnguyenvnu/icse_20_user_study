package com.example.jingbin.cloudreader.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Environment;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by jingbin on 16/9/20.
 */
public class QRCodeUtil {
    /**
     * 生�?二维�?Bitmap
     *
     * @param content   内容
     * @param widthPix  图片宽度
     * @param heightPix 图片高度
     * @param logoBm    二维�?中心的Logo图标（�?�以为null）
     * @param filePath  用于存储二维�?图片的文件路径
     * @return 生�?二维�?�?��?存文件是�?��?功
     */
    public static boolean createQRImage(String content, int widthPix, int heightPix, Bitmap logoBm, String filePath) {
        try {
            if (content == null || "".equals(content)) {
                return false;
            }

            //�?置�?�数
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
            //设置空白边�?的宽度
//            hints.put(EncodeHintType.MARGIN, 2); //default is 4

            // 图�?数�?�转�?�，使用了矩阵转�?�
            BitMatrix bitMatrix = new QRCodeWriter().encode(content, BarcodeFormat.QR_CODE, widthPix, heightPix, hints);
            int[] pixels = new int[widthPix * heightPix];
            // 下�?�这里按照二维�?的算法，�?个生�?二维�?的图片，
            // 两个for循环是图片横列扫�??的结果
            for (int y = 0; y < heightPix; y++) {
                for (int x = 0; x < widthPix; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * widthPix + x] = 0xff000000;
                    } else {
                        pixels[y * widthPix + x] = 0xffffffff;
                    }
                }
            }

            // 生�?二维�?图片的格�?，使用ARGB_8888
            Bitmap bitmap = Bitmap.createBitmap(widthPix, heightPix, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, widthPix, 0, 0, widthPix, heightPix);

            if (logoBm != null) {
                bitmap = addLogo(bitmap, logoBm);
            }

            //必须使用compress方法将bitmap�?存到文件中�?进行读�?�。直接返回的bitmap是没有任何压缩的，内存消耗巨大�?
            return bitmap != null && bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new FileOutputStream(filePath));
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 在二维�?中间添加Logo图案
     */
    private static Bitmap addLogo(Bitmap src, Bitmap logo) {
        if (src == null) {
            return null;
        }

        if (logo == null) {
            return src;
        }

        //获�?�图片的宽高
        int srcWidth = src.getWidth();
        int srcHeight = src.getHeight();
        int logoWidth = logo.getWidth();
        int logoHeight = logo.getHeight();

        if (srcWidth == 0 || srcHeight == 0) {
            return null;
        }

        if (logoWidth == 0 || logoHeight == 0) {
            return src;
        }

        //logo大�?为二维�?整体大�?的1/5
        float scaleFactor = srcWidth * 1.0f / 5 / logoWidth;
        Bitmap bitmap = Bitmap.createBitmap(srcWidth, srcHeight, Bitmap.Config.ARGB_8888);
        try {
            Canvas canvas = new Canvas(bitmap);
            canvas.drawBitmap(src, 0, 0, null);
            canvas.scale(scaleFactor, scaleFactor, srcWidth / 2, srcHeight / 2);
            canvas.drawBitmap(logo, (srcWidth - logoWidth) / 2, (srcHeight - logoHeight) / 2, null);

//            canvas.save(Canvas.ALL_SAVE_FLAG);
            canvas.save();
            canvas.restore();
        } catch (Exception e) {
            bitmap = null;
            e.getStackTrace();
        }

        return bitmap;
    }

    //文件存储根目录
    private static String getFileRoot(Context context) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File external = context.getExternalFilesDir(null);
            if (external != null) {
                return external.getAbsolutePath();
            }
        }

        return context.getFilesDir().getAbsolutePath();
    }

    /**
     * @param text        生�?二维�?的字符串
     * @param imageView   显示二维�?的ImageView
     * @param centerPhoto 二维�?中间的图片
     */
    public static void showThreadImage(final Activity mContext, final String text, final ImageView imageView, final int centerPhoto) {
        String preContent = SPUtils.getString("share_code_content","");
        if (text.equals(preContent)) {
            String preFilePath = SPUtils.getString("share_code_filePath","");
            imageView.setImageBitmap(BitmapFactory.decodeFile(preFilePath));

        } else {
            SPUtils.putString("share_code_content",text);
            final String filePath = getFileRoot(mContext) + File.separator + "qr_" + System.currentTimeMillis() + ".jpg";
            SPUtils.putString("share_code_filePath",filePath);

            //二维�?图片较大时，生�?图片�?�?存文件的时间�?�能较长，因此放在新线程中
            new Thread(new Runnable() {
                @Override
                public void run() {
                    boolean success = QRCodeUtil.createQRImage(text, 800, 800, BitmapFactory.decodeResource(mContext.getResources(), centerPhoto),
                            filePath);

                    if (success) {
                        mContext.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(BitmapFactory.decodeFile(filePath));
                            }
                        });
                    }
                }
            }).start();
        }

    }
}

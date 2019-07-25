package com.cg.baseproject.utils.android.image;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

import com.cg.baseproject.utils.android.ResolutionAdaptationUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * @author wlj
 * @date 2017/3/29
 * @email wanglijundev@gmail.com
 * @packagename wanglijun.vip.androidutils.utils
 * @desc: bitmap图片的相互转�?�工具类
 */

public class ImageUtils {
    public static final int UNSPECIFIED = 0;

    /**
     * convert Bitmap to byte array
     */
    public static byte[] bitmapToByte(Bitmap b) {
        ByteArrayOutputStream o = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, o);
        return o.toByteArray();
    }

    /**
     * convert byte array to Bitmap
     */
    public static Bitmap byteToBitmap(byte[] b) {
        return (b == null || b.length == 0) ? null : BitmapFactory.decodeByteArray(b, 0, b.length);
    }

    /**
     * 把bitmap转�?��?Base64编�?String
     */
    public static String bitmapToString(Bitmap bitmap) {
        return Base64.encodeToString(bitmapToByte(bitmap), Base64.DEFAULT);
    }

    /**
     * convert Drawable to Bitmap
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
    }

    /**
     * convert Bitmap to Drawable
     */
    public static Drawable bitmapToDrawable(Bitmap bitmap) {
        return bitmap == null ? null : new BitmapDrawable(bitmap);
    }

    /**
     * scale image
     */
    public static Bitmap scaleImageTo(Bitmap org, int newWidth, int newHeight) {
        return scaleImage(org, (float) newWidth / org.getWidth(), (float) newHeight / org.getHeight());
    }

    /**
     * scale image
     */
    public static Bitmap scaleImage(Bitmap org, float scaleWidth, float scaleHeight) {
        if (org == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(org, 0, 0, org.getWidth(), org.getHeight(), matrix, true);
    }

    /**
     * 圆形图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getCircleBitmap(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }
    
    /**
     * 获�?�圆角
     *
     * @param bitmap
     * @param pixels 角度
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        if (bitmap != null) {
            final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                    bitmap.getHeight(), Bitmap.Config.ARGB_8888);
            if (output != null) {
                final Canvas canvas = new Canvas(output);
                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                final RectF rectF = new RectF(rect);
                final float roundPx = pixels;
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
                canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
                paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rect, paint);
                return output;
            }
        }

        return null;
    }

    public static Bitmap toRoundCorner(Bitmap bitmap) {
        int height = bitmap.getHeight();
        int width = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, width, height);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        //paint.setColor(Color.TRANSPARENT);
        canvas.drawCircle(width / 2, height / 2, width / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap createBitmapThumbnail(Bitmap bitMap, boolean needRecycle) {
        int width = bitMap.getWidth();
        int height = bitMap.getHeight();
        // 设置想�?的大�?
        int newWidth = 120;
        int newHeight = 120;
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // �?�得想�?缩放的matrix�?�数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newBitMap = Bitmap.createBitmap(bitMap, 0, 0, width, height,
                matrix, true);
        if (needRecycle) bitMap.recycle();
        return newBitMap;
    }

    public static boolean saveBitmap(Bitmap bitmap, File file) {
        if (bitmap == null) return false;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public static boolean saveBitmap(Bitmap bitmap, String absPath) {
        return saveBitmap(bitmap, new File(absPath));
    }


    /**
     * 计算图片的缩放值
     * 如果图片的原始高度或者宽度大与我们期望的宽度和高度，我们需�?计算出缩放比例的数值。�?�则就�?缩放。
     * heightRatio是图片原始高度与压缩�?�高度的�?数，
     * widthRatio是图片原始宽度与压缩�?�宽度的�?数。
     * inSampleSize就是缩放值 ，�?�heightRatio与widthRatio中最�?的值。
     * inSampleSize为1表示宽度和高度�?缩放，为2表示压缩�?�的宽度与高度为原�?�的1/2(图片为原1/4)。
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will guarantee
            // a final image with both dimensions(尺寸) larger than or equal to the requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    /**
     * 根�?�路径获得图片并压缩返回bitmap用于显示
     *
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath, int w, int h) {
        final BitmapFactory.Options options = new BitmapFactory.Options();

        //该值设为true那么将�?返回实际的bitmap�?给其分�?内存空间而里�?��?�包括一些解�?边界信�?��?�图片大�?信�?�
        options.inJustDecodeBounds = true;//inJustDecodeBounds设置为true，�?�以�?把图片读到内存中,但�?然�?�以计算出图片的大�?
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, w, h);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;//�?新读入图片，注�?这次�?把options.inJustDecodeBounds 设为 false
        Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);// BitmapFactory.decodeFile()按指定大�?�?�得图片缩略图
        return bitmap;
    }

    public static Intent buildGalleryPickIntent(Uri saveTo, int aspectX, int aspectY,
                                                int outputX, int outputY, boolean returnData) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", saveTo);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }

    public static Intent buildImagePickIntent(Uri uriFrom, Uri uriTo, int aspectX, int aspectY,
                                              int outputX, int outputY, boolean returnData) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uriFrom, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("output", uriTo);
        intent.putExtra("aspectX", aspectX);
        intent.putExtra("aspectY", aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", true);
        intent.putExtra("return-data", returnData);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());
        return intent;
    }


    public static Intent buildCaptureIntent(Uri uri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;
    }
    
	public static Bitmap decodeUriAsBitmap(Uri uri) {
        Bitmap bitmap = null;
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(new URI(uri.toString()).getPath())));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            int i = 0;
            while (true) {
                if ((options.outWidth >> i <= 1000)
                        && (options.outHeight >> i <= 1000)) {
                    in = new BufferedInputStream(
                            new FileInputStream(new File(new File(new URI(uri.toString())).getPath())));
                    options.inSampleSize = (int) Math.pow(2.0D, i);
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapFactory.decodeStream(in, null, options);
                    break;
                }
                i += 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
	
    /**
     * Convert resId to drawable
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable resToDrawable(Context context, int resId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return context.getDrawable(resId);
        }
        return context.getResources().getDrawable(resId);
    }

    /**
     * Convert byte array to Drawable
     *
     * @param b
     * @return
     */
    public static Drawable byteToDrawable(byte[] b) {
        return bitmapToDrawable(byteToBitmap(b));
    }

    /**
     * Convert view to bitmap
     *
     * @param view
     * @param width
     * @param height
     * @return
     */
    public static Bitmap convertViewToBitmap(View view, int width, int height) {
        view.measure(View.MeasureSpec.makeMeasureSpec(width, (width == UNSPECIFIED) ? View.MeasureSpec.UNSPECIFIED :
                        View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(height, (height == UNSPECIFIED) ? View.MeasureSpec.UNSPECIFIED :
                        View.MeasureSpec.EXACTLY));
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        view.draw(new Canvas(bitmap));
        return bitmap;
    }

    /**
     * Convert view to bitmap
     *
     * @param view
     * @return
     */
    public static Bitmap convertViewToBitmap(View view) {
        return convertViewToBitmap(view, UNSPECIFIED, UNSPECIFIED);
    }

    /**
     * Resize image by width and height
     *
     * @param originalBitmap
     * @param w
     * @param h
     * @return
     */
    public static Bitmap resizeImage(Bitmap originalBitmap, int w, int h) {
        if (originalBitmap == null) {
            return null;
        }
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        if (width <= w && height <= h) {
            return originalBitmap;
        }
        float screenRatio = (float) w / h;
        float ratio = (float) width / height;
        if (screenRatio >= ratio) {
            width = (int) (h * ratio);
            height = h;
        } else {
            height = (int) (w / ratio);
            width = w;
        }
        return Bitmap.createScaledBitmap(originalBitmap, width, height, true);
    }

    /**
     * Decode bitmap 从文件中获�?�图片
     *
     * @param is
     * @param context
     * @return
     * @throws IOException
     */
    public static Bitmap decodeBitmap(InputStream is, Context context) throws IOException {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 设置�?了true,�?�?�用内存，�?�获�?�bitmap宽高
        byte[] data = isToByte(is);//将InputStream转为byte数组，�?�以多次读�?�
//        BitmapFactory.decodeStream(is, null, options);InputStream�?�?�能被读�?�一次，下次读�?�就为空了。
        BitmapFactory.decodeByteArray(data, 0, data.length, options);
        options.inSampleSize = calculateInSampleSize(options, context); // 调用上�?�定义的方法计算inSampleSize值
        // 使用获�?�到的inSampleSize值�?次解�?图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, 0, data.length, options);
    }

    /**
     * Calculate inSampleSize
     *
     * @param options
     * @param context
     * @return
     */
    private static int calculateInSampleSize(BitmapFactory.Options options, Context context) {
        // �?图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        int h = ResolutionAdaptationUtils.getScreenHeight(context);
        int w = ResolutionAdaptationUtils.getScreenWeight(context);
        if (height > h || width > w) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) h);
            final int widthRatio = Math.round((float) width / (float) w);
            // 选择宽和高中最�?的比率作为inSampleSize的值，这样�?�以�?�?最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * Convert InputStream to byte array
     *
     * @param is
     * @return
     * @throws IOException
     */
    private static byte[] isToByte(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buff = new byte[1024];
        int len = 0;
        while ((len = is.read(buff)) != -1) {
            baos.write(buff, 0, len);
        }
        is.close();
        baos.close();
        return baos.toByteArray();
    }

    /**
     * take a screenshot
     *
     * @param activity
     * @param filePath
     * @return
     */
    public static boolean screenshot(Activity activity, String filePath) {
        View decorView = activity.getWindow().getDecorView();
        decorView.setDrawingCacheEnabled(true);
        decorView.buildDrawingCache();
        Bitmap bitmap = decorView.getDrawingCache();
        File imagePath = new File(filePath);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                if (null != bitmap) {
                    bitmap.recycle();
                    bitmap = null;
                }
            } catch (Exception e) {
            }
            decorView.destroyDrawingCache();
            decorView.setDrawingCacheEnabled(false);
        }
        return false;
    }

    /**
     * Combine bitmaps
     *
     * @param bgBitmap 背景Bitmap
     * @param fgBitmap �?景Bitmap
     * @return �?��?�?�的Bitmap
     */
    public static Bitmap combineBitmap(Bitmap bgBitmap, Bitmap fgBitmap) {
        Bitmap bmp;

        int width = bgBitmap.getWidth() > fgBitmap.getWidth() ? bgBitmap.getWidth() : fgBitmap
                .getWidth();
        int height = bgBitmap.getHeight() > fgBitmap.getHeight() ? bgBitmap.getHeight() : fgBitmap
                .getHeight();

        bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bgBitmap, 0, 0, null);
        canvas.drawBitmap(fgBitmap, 0, 0, paint);

        return bmp;
    }

    /**
     * Combine bitmaps
     *
     * @param bgd �?�景Bitmap
     * @param fg  �?景Bitmap
     * @return �?��?�?�Bitmap
     */
    public static Bitmap combineBitmapInSameSize(Bitmap bgd, Bitmap fg) {
        Bitmap bmp;

        int width = bgd.getWidth() < fg.getWidth() ? bgd.getWidth() : fg
                .getWidth();
        int height = bgd.getHeight() < fg.getHeight() ? bgd.getHeight() : fg
                .getHeight();

        if (fg.getWidth() != width && fg.getHeight() != height) {
            fg = zoom(fg, width, height);
        }
        if (bgd.getWidth() != width && bgd.getHeight() != height) {
            bgd = zoom(bgd, width, height);
        }

        bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        Canvas canvas = new Canvas(bmp);
        canvas.drawBitmap(bgd, 0, 0, null);
        canvas.drawBitmap(fg, 0, 0, paint);

        return bmp;
    }

    /**
     * zoom bitmap
     *
     * @param bitmap �?Bitmap
     * @param w      宽
     * @param h      高
     * @return 目标Bitmap
     */
    public static Bitmap zoom(Bitmap bitmap, int w, int h) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidht = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidht, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                matrix, true);
        return newbmp;
    }

    /**
     * Get rounded corner bitmap
     *
     * @param bitmap
     * @param roundPx 圆角大�?
     * @return
     */
    public static Bitmap createRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * Get reflection bitmap
     *
     * @param bitmap �?Bitmap
     * @return 带倒影的Bitmap
     */
    public static Bitmap createReflectionBitmap(Bitmap bitmap) {
        final int reflectionGap = 4;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);

        Bitmap reflectionImage = Bitmap.createBitmap(bitmap, 0, height / 2,
                width, height / 2, matrix, false);

        Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
                (height + height / 2), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmapWithReflection);
        canvas.drawBitmap(bitmap, 0, 0, null);
        Paint deafalutPaint = new Paint();
        canvas.drawRect(0, height, width, height + reflectionGap, deafalutPaint);

        canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, bitmap.getHeight(), 0,
                bitmapWithReflection.getHeight() + reflectionGap, 0x70ffffff,
                0x00ffffff, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        // Set the Transfer mode to be porter duff and destination in
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        // Draw a rectangle using the paint with our linear gradient
        canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
                + reflectionGap, paint);

        return bitmapWithReflection;
    }

    /**
     * Compress bitmap
     *
     * @param bmp �?Bitmap
     * @return 压缩�?�的Bitmap
     */
    public static Bitmap compressBitmap(Bitmap bmp) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);// 质�?压缩方法，这里100表示�?压缩，把压缩�?�的数�?�存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { // 循环判断如果压缩�?�图片是�?�大于100kb,大于继续压缩
            baos.reset();// �?置baos�?�清空baos
            bmp.compress(Bitmap.CompressFormat.JPEG, options, baos);// 这里压缩options%，把压缩�?�的数�?�存放到baos中
            options -= 10;// �?次都�?少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// 把压缩�?�的数�?�baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// 把ByteArrayInputStream数�?�生�?图片
        return bitmap;
    }

    /**
     * 将彩色图转�?�为�?�度图
     *
     * @param img �?Bitmap
     * @return 返回转�?�好的�?图
     */
    public static Bitmap convertGreyImg(Bitmap img) {
        int width = img.getWidth(); // 获�?��?图的宽
        int height = img.getHeight(); // 获�?��?图的高

        int[] pixels = new int[width * height]; // 通过�?图的大�?创建�?素点数组

        img.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) ((float) red * 0.3 + (float) green * 0.59 + (float) blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap result = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        result.setPixels(pixels, 0, width, 0, 0, width, height);
        return result;
    }

    /**
     * Get round bitmap
     *
     * @param bitmap
     * @return
     */
    public static Bitmap createRoundBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
                (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
                (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    /**
     * Returns a Bitmap representing the thumbnail of the specified Bitmap.
     *
     * @param bitmap
     * @param context
     * @return
     */
    public static Bitmap createThumbnailBitmap(Bitmap bitmap, Context context) {
        int sIconWidth = -1;
        int sIconHeight = -1;
        final Resources resources = context.getResources();
        sIconWidth = sIconHeight = (int) resources
                .getDimension(android.R.dimen.app_icon_size);

        final Paint sPaint = new Paint();
        final Rect sBounds = new Rect();
        final Rect sOldBounds = new Rect();
        Canvas sCanvas = new Canvas();

        int width = sIconWidth;
        int height = sIconHeight;

        sCanvas.setDrawFilter(new PaintFlagsDrawFilter(Paint.DITHER_FLAG,
                Paint.FILTER_BITMAP_FLAG));

        final int bitmapWidth = bitmap.getWidth();
        final int bitmapHeight = bitmap.getHeight();

        if (width > 0 && height > 0) {
            if (width < bitmapWidth || height < bitmapHeight) {
                final float ratio = (float) bitmapWidth / bitmapHeight;

                if (bitmapWidth > bitmapHeight) {
                    height = (int) (width / ratio);
                } else if (bitmapHeight > bitmapWidth) {
                    width = (int) (height * ratio);
                }

                final Bitmap.Config c = (width == sIconWidth && height == sIconHeight) ? bitmap
                        .getConfig() : Bitmap.Config.ARGB_8888;
                final Bitmap thumb = Bitmap.createBitmap(sIconWidth,
                        sIconHeight, c);
                final Canvas canvas = sCanvas;
                final Paint paint = sPaint;
                canvas.setBitmap(thumb);
                paint.setDither(false);
                paint.setFilterBitmap(true);
                sBounds.set((sIconWidth - width) / 2,
                        (sIconHeight - height) / 2, width, height);
                sOldBounds.set(0, 0, bitmapWidth, bitmapHeight);
                canvas.drawBitmap(bitmap, sOldBounds, sBounds, paint);
                return thumb;
            } else if (bitmapWidth < width || bitmapHeight < height) {
                final Bitmap.Config c = Bitmap.Config.ARGB_8888;
                final Bitmap thumb = Bitmap.createBitmap(sIconWidth,
                        sIconHeight, c);
                final Canvas canvas = sCanvas;
                final Paint paint = sPaint;
                canvas.setBitmap(thumb);
                paint.setDither(false);
                paint.setFilterBitmap(true);
                canvas.drawBitmap(bitmap, (sIconWidth - bitmapWidth) / 2,
                        (sIconHeight - bitmapHeight) / 2, paint);
                return thumb;
            }
        }

        return bitmap;
    }

    

    /**
     * �?新编�?Bitmap
     *
     * @param src     需�?�?新编�?的Bitmap
     * @param format  编�?�?�的格�?（目�?�?�支�?png和jpeg这两�?格�?）
     * @param quality �?新生�?�?�的bitmap的质�?
     * @return 返回�?新生�?�?�的bitmap
     */
    public static Bitmap decodeBitmap(Bitmap src, Bitmap.CompressFormat format,
                                      int quality) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        src.compress(format, quality, os);

        byte[] array = os.toByteArray();
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }

    /**
     * 图片压缩，如果bitmap本身的大�?�?于maxSize，则�?作处�?�
     *
     * @param bitmap  �?压缩的图片
     * @param maxSize 压缩�?�的大�?，�?��?kb
     */
    public static void compressBitmap(Bitmap bitmap, double maxSize) {
        // 将bitmap放至数组中，�?在获得bitmap的大�?（与实际读�?�的原文件�?大）
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 格�?�?质�?�?输出�?
        bitmap.compress(Bitmap.CompressFormat.PNG, 70, baos);
        byte[] b = baos.toByteArray();
        // 将字节�?��?KB
        double mid = b.length / 1024;
        // 获�?�bitmap大�? 是�?许最大大�?的多少�?
        double i = mid / maxSize;
        // 判断bitmap�?�用空间是�?�大于�?许最大空间 如果大于则压缩 �?于则�?压缩
        if (i > 1) {
            // 缩放图片 此处用到平方根 将宽带和高度压缩掉对应的平方根�?
            // （�?�?宽高�?�?�，缩放�?�也达到了最大�?�用空间的大�?）
            bitmap = scale(bitmap, bitmap.getWidth() / Math.sqrt(i),
                    bitmap.getHeight() / Math.sqrt(i));
        }
    }

    /**
     * scale bitmap
     *
     * @param src
     * @param newWidth
     * @param newHeight
     * @return
     */
    public static Bitmap scale(Bitmap src, double newWidth, double newHeight) {
        // 记录src的宽高
        float width = src.getWidth();
        float height = src.getHeight();
        // 创建一个matrix容器
        Matrix matrix = new Matrix();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 开始缩放
        matrix.postScale(scaleWidth, scaleHeight);
        // 创建缩放�?�的图片
        return Bitmap.createBitmap(src, 0, 0, (int) width, (int) height,
                matrix, true);
    }

    /**
     * scale bitmap
     *
     * @param src
     * @param scaleMatrix
     * @return
     */
    public static Bitmap scale(Bitmap src, Matrix scaleMatrix) {
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(),
                scaleMatrix, true);
    }

    /**
     * scale bitmap
     *
     * @param src
     * @param scaleX
     * @param scaleY
     * @return
     */
    public static Bitmap scale(Bitmap src, float scaleX, float scaleY) {
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        return Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(),
                matrix, true);
    }

    /**
     * scale bitmap with the same scale
     *
     * @param src
     * @param scale
     * @return
     */
    public static Bitmap scale(Bitmap src, float scale) {
        return scale(src, scale, scale);
    }

    /**
     * rotate bitmap
     *
     * @param bitmap
     * @param angle
     * @return
     */
    public static Bitmap rotate(Bitmap bitmap, int angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }

    /**
     * 水平翻转处�?�
     *
     * @param bitmap 原图
     * @return 水平翻转�?�的图片
     */
    public static Bitmap reverseHorizontal(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(-1, 1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, false);
    }

    /**
     * 垂直翻转处�?�
     *
     * @param bitmap 原图
     * @return 垂直翻转�?�的图片
     */
    public static Bitmap reverseVertical(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        matrix.preScale(1, -1);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, false);
    }

    /**
     * 更改图片色系，�?�亮或�?�暗
     *
     * @param delta 图片的亮暗程度值，越�?图片会越亮，�?�值范围(0,24)
     * @return
     */
    public static Bitmap adjustTone(Bitmap src, int delta) {
        if (delta >= 24 || delta <= 0) {
            return null;
        }
        // 设置高斯矩阵
        int[] gauss = new int[]{1, 2, 1, 2, 4, 2, 1, 2, 1};
        int width = src.getWidth();
        int height = src.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int pixColor = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        int idx = 0;
        int[] pixels = new int[width * height];

        src.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR += (pixR * gauss[idx]);
                        newG += (pixG * gauss[idx]);
                        newB += (pixB * gauss[idx]);
                        idx++;
                    }
                }
                newR /= delta;
                newG /= delta;
                newB /= delta;
                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));
                pixels[i * width + k] = Color.argb(255, newR, newG, newB);
                newR = 0;
                newG = 0;
                newB = 0;
            }
        }
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 将彩色图转�?�为黑白图
     *
     * @param bmp �?图
     * @return 返回转�?�好的�?图
     */
    public static Bitmap convertToBlackWhite(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);

        int alpha = 0xFF << 24; // 默认将bitmap当�?24色图片
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap newBmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBmp;
    }

    /**
     * 读�?�图片属性：图片被旋转的角度
     *
     * @param path 图片�?对路径
     * @return 旋转的角度
     */
    public static int getImageDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    /**
     * 饱和度处�?�
     *
     * @param bitmap          原图
     * @param saturationValue 新的饱和度值
     * @return 改�?�了饱和度值之�?�的图片
     */
    public static Bitmap saturation(Bitmap bitmap, int saturationValue) {
        // 计算出符�?��?求的饱和度值
        float newSaturationValue = saturationValue * 1.0F / 127;
        // 创建一个颜色矩阵
        ColorMatrix saturationColorMatrix = new ColorMatrix();
        // 设置饱和度值
        saturationColorMatrix.setSaturation(newSaturationValue);
        // 创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(saturationColorMatrix));
        // 创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        // 将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 亮度处�?�
     *
     * @param bitmap   原图
     * @param lumValue 新的亮度值
     * @return 改�?�了亮度值之�?�的图片
     */
    public static Bitmap lum(Bitmap bitmap, int lumValue) {
        // 计算出符�?��?求的亮度值
        float newlumValue = lumValue * 1.0F / 127;
        // 创建一个颜色矩阵
        ColorMatrix lumColorMatrix = new ColorMatrix();
        // 设置亮度值
        lumColorMatrix.setScale(newlumValue, newlumValue, newlumValue, 1);
        // 创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(lumColorMatrix));
        // 创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        // 将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 色相处�?�
     *
     * @param bitmap   原图
     * @param hueValue 新的色相值
     * @return 改�?�了色相值之�?�的图片
     */
    public static Bitmap hue(Bitmap bitmap, int hueValue) {
        // 计算出符�?��?求的色相值
        float newHueValue = (hueValue - 127) * 1.0F / 127 * 180;
        // 创建一个颜色矩阵
        ColorMatrix hueColorMatrix = new ColorMatrix();
        // 控制让红色区在色轮上旋转的角度
        hueColorMatrix.setRotate(0, newHueValue);
        // 控制让绿红色区在色轮上旋转的角度
        hueColorMatrix.setRotate(1, newHueValue);
        // 控制让�?色区在色轮上旋转的角度
        hueColorMatrix.setRotate(2, newHueValue);
        // 创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(hueColorMatrix));
        // 创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        // 将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 亮度�?色相�?饱和度处�?�
     *
     * @param bitmap          原图
     * @param lumValue        亮度值
     * @param hueValue        色相值
     * @param saturationValue 饱和度值
     * @return 亮度�?色相�?饱和度处�?��?�的图片
     */
    public static Bitmap lumAndHueAndSaturation(Bitmap bitmap, int lumValue,
                                                int hueValue, int saturationValue) {
        // 计算出符�?��?求的饱和度值
        float newSaturationValue = saturationValue * 1.0F / 127;
        // 计算出符�?��?求的亮度值
        float newlumValue = lumValue * 1.0F / 127;
        // 计算出符�?��?求的色相值
        float newHueValue = (hueValue - 127) * 1.0F / 127 * 180;

        // 创建一个颜色矩阵并设置其饱和度
        ColorMatrix colorMatrix = new ColorMatrix();

        // 设置饱和度值
        colorMatrix.setSaturation(newSaturationValue);
        // 设置亮度值
        colorMatrix.setScale(newlumValue, newlumValue, newlumValue, 1);
        // 控制让红色区在色轮上旋转的角度
        colorMatrix.setRotate(0, newHueValue);
        // 控制让绿红色区在色轮上旋转的角度
        colorMatrix.setRotate(1, newHueValue);
        // 控制让�?色区在色轮上旋转的角度
        colorMatrix.setRotate(2, newHueValue);

        // 创建一个画笔并设置其颜色过滤器
        Paint paint = new Paint();
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        // 创建一个新的图片并创建画布
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        // 将原图使用给定的画笔画到画布上
        canvas.drawBitmap(bitmap, 0, 0, paint);
        return newBitmap;
    }

    /**
     * 怀旧效果
     *
     * @param bitmap
     * @return
     */
    public static Bitmap nostalgic(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);
        int pixColor = 0;
        int pixR = 0;
        int pixG = 0;
        int pixB = 0;
        int newR = 0;
        int newG = 0;
        int newB = 0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < height; i++) {
            for (int k = 0; k < width; k++) {
                pixColor = pixels[width * i + k];
                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);
                newR = (int) (0.393 * pixR + 0.769 * pixG + 0.189 * pixB);
                newG = (int) (0.349 * pixR + 0.686 * pixG + 0.168 * pixB);
                newB = (int) (0.272 * pixR + 0.534 * pixG + 0.131 * pixB);
                int newColor = Color.argb(255, newR > 255 ? 255 : newR,
                        newG > 255 ? 255 : newG, newB > 255 ? 255 : newB);
                pixels[width * i + k] = newColor;
            }
        }
        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 柔化效果
     *
     * @param bitmap
     * @return
     */
    public static Bitmap soften(Bitmap bitmap) {
        // 高斯矩阵
        int[] gauss = new int[]{1, 2, 1, 2, 4, 2, 1, 2, 1};

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 16; // 值越�?图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 光照效果
     *
     * @param bitmap
     * @param centerX 光�?在X轴的�?置
     * @param centerY 光�?在Y轴的�?置
     * @return
     */
    public static Bitmap sunshine(Bitmap bitmap, int centerX, int centerY) {
        final int width = bitmap.getWidth();
        final int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;
        int radius = Math.min(centerX, centerY);

        final float strength = 150F; // 光照强度 100~150
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int pos = 0;
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                pos = i * width + k;
                pixColor = pixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newR = pixR;
                newG = pixG;
                newB = pixB;

                // 计算当�?点到光照中心的�?离，平�?�座标系中求两点之间的�?离
                int distance = (int) (Math.pow((centerY - i), 2) + Math.pow(
                        centerX - k, 2));
                if (distance < radius * radius) {
                    // 按照�?离大�?计算增加的光照值
                    int result = (int) (strength * (1.0 - Math.sqrt(distance)
                            / radius));
                    newR = pixR + result;
                    newG = pixG + result;
                    newB = pixB + result;
                }

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[pos] = Color.argb(255, newR, newG, newB);
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 底片效果
     *
     * @param bitmap
     * @return
     */
    public static Bitmap film(Bitmap bitmap) {
        // RGBA的最大值
        final int MAX_VALUE = 255;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int pos = 0;
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                pos = i * width + k;
                pixColor = pixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                newR = MAX_VALUE - pixR;
                newG = MAX_VALUE - pixG;
                newB = MAX_VALUE - pixB;

                newR = Math.min(MAX_VALUE, Math.max(0, newR));
                newG = Math.min(MAX_VALUE, Math.max(0, newG));
                newB = Math.min(MAX_VALUE, Math.max(0, newB));

                pixels[pos] = Color.argb(MAX_VALUE, newR, newG, newB);
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * �?化效果
     *
     * @param bitmap
     * @return
     */
    public static Bitmap sharpen(Bitmap bitmap) {
        // 拉普拉斯矩阵
        int[] laplacian = new int[]{-1, -1, -1, -1, 9, -1, -1, -1, -1};

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int idx = 0;
        float alpha = 0.3F;
        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                for (int m = -1; m <= 1; m++) {
                    for (int n = -1; n <= 1; n++) {
                        pixColor = pixels[(i + n) * width + k + m];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * laplacian[idx] * alpha);
                        newG = newG + (int) (pixG * laplacian[idx] * alpha);
                        newB = newB + (int) (pixB * laplacian[idx] * alpha);
                        idx++;
                    }
                }

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);
                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 浮雕效果
     *
     * @param bitmap
     * @return
     */
    public static Bitmap emboss(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap newBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int[] pixels = new int[width * height];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int pos = 0;
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                pos = i * width + k;
                pixColor = pixels[pos];

                pixR = Color.red(pixColor);
                pixG = Color.green(pixColor);
                pixB = Color.blue(pixColor);

                pixColor = pixels[pos + 1];
                newR = Color.red(pixColor) - pixR + 127;
                newG = Color.green(pixColor) - pixG + 127;
                newB = Color.blue(pixColor) - pixB + 127;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[pos] = Color.argb(255, newR, newG, newB);
            }
        }

        newBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBitmap;
    }

    /**
     * 长度�?��?转�?�
     * @param context
     * @param unit
     * @param value
     * @return
     */
    public static float applyDimension(Context context,int unit, float value){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(unit, value, metrics);
    }

    /**
     * 图片旋转
     *
     * @param bm
     * 图片资�?Bitmap
     * @param curDegrees
     * //当�?旋转度数
     */
    public static Bitmap rotateBitmap(Bitmap bm, float curDegrees) {
        return rotateBitmap(bm, curDegrees, true);
    }

    public static Bitmap rotateBitmap(Bitmap bm, float curDegrees, boolean isRecycled) {
        if (bm == null) {
            return null;
        }
        final int bmpW = bm.getWidth();
        final int bmpH = bm.getHeight();
        // 注�?这个Matirx是android.graphics底下的那个
        final Matrix mt = new Matrix();
        mt.reset();
        mt.setRotate(curDegrees);
        final Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bmpW, bmpH, mt,
                true);

        if (isRecycled && !bm.isRecycled()) {
            bm.recycle();
        }
        return bitmap;
    }

    /**
     * 旋转图片
     * @param path 图片路径
     * @param bitmap 原图
     * @return
     */
    public static Bitmap rotaingImageView(String path, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        final int angle = readPictureDegree(path);
        if (angle != 0) {
            matrix.postRotate(angle);
        }
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 判断图片旋转情况
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            final ExifInterface exifInterface = new ExifInterface(path);
            final int orientation =
                    exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 按比例缩�?图片（�?��?�?素） lessen the bitmap
     *
     * @param resId
     * bitmap
     * @param destWidth
     * the dest bitmap width
     * @param destHeigth
     * @return new bitmap if successful ,oherwise null
     */
    public static Bitmap lessenBitmap(Context context, int resId, int destWidth, int destHeigth) {

        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获�?�资�?图片
        final InputStream is = context.getResources().openRawResource(resId);
        final Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);

        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int w = bitmap.getWidth();// �?文件的大�?
        final int h = bitmap.getHeight();
        float scaleWidth = ((float) destWidth) / w;// 宽度缩�?比例
        float scaleHeight = ((float) destHeigth) / h;// 高度缩�?比例
        final Matrix m = new Matrix();// 矩阵
        m.postScale(scaleWidth, scaleHeight);// 设置矩阵比例
        final Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, m, true);// 直接按照矩阵的比例把�?文件画入进

        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }

        return resizedBitmap;
    }

    /**
     *
     * 从Assets中读�?�图片
     * @param filepath 相对路径
     * @return Bitmap
     */
    public static Bitmap getImageFromAssetsFile(String filepath, Context context) {
        Bitmap image = null;
        InputStream is = null;
        AssetManager am = context.getResources().getAssets();
        try {
            is = am.open(filepath);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return image;
    }

    /**
     * 获�?�缩略图
     *
     * @param bitmap
     * 是�?�转�?缩略图
     * @return
     */
    public static Bitmap decodeBitmapToThumbnail(Bitmap bitmap) {
        return decodeBitmapToThumbnail(bitmap, true);
    }

    /**
     * 缩略图
     * @param bitmap
     * @param isThumbnail
     * @return
     */
    public static Bitmap decodeBitmapToThumbnail(Bitmap bitmap,
                                                 boolean isThumbnail) {
        if (isThumbnail) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            final float realWidth = options.outWidth;
            final float realHeight = options.outHeight;

            // 计算缩放比
            int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
            if (scale <= 0) {
                scale = 1;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            // 注�?这次�?把options.inJustDecodeBounds 设为 false,这次图片是�?读�?�出�?�的。
            final byte[] data = getBitmap2Byte(bitmap);
            if (data != null) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,
                        options);
            }

        }
        return bitmap;
    }

    /**
     * bitmap转byte[]
     *
     * @param bitmap
     * @return
     */
    public static byte[] getBitmap2Byte(Bitmap bitmap) {
        if (bitmap != null) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            final byte[] data = baos.toByteArray();
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        return null;
    }
    
    /**
     * 图片�?明度处�?�
     *
     * @param sourceImg
     * 原始图片
     * @param number
     * �?明度
     * @return
     */
    public static Bitmap setAlpha(Bitmap sourceImg, int number) {
        try {
            int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
            sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0,
                    sourceImg.getWidth(), sourceImg.getHeight());// 获得图片的ARGB值
            number = number * 255 / 100;
            for (int i = 0; i < argb.length; i++) {
                if ((argb[i] & 0xff000000) != 0x00000000) {// �?明色�?�?�处�?�
                    argb[i] = (number << 24) | (argb[i] & 0xFFFFFF);// 修改最高2�?的值
                }
            }
            sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(),
                    sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return sourceImg;
    }

}

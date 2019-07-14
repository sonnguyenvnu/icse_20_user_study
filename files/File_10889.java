package com.vondear.rxtool;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author vondear
 * @date 2016/1/24
 * 相机相关工具类
 */
public class RxPictureTool {

    /**
     * 获�?�打开照程�?界�?�的Intent
     */
    public static Intent getOpenCameraIntent() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    /**
     * 获�?�跳转至相册选择界�?�的Intent
     */
    public static Intent getImagePickerIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        return intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
    }

    /**
     * 获�?�[跳转至相册选择界�?�,并跳转至�?剪界�?�，默认�?�缩放�?剪区域]的Intent
     */
    public static Intent getImagePickerIntent(int outputX, int outputY, Uri fromFileURI,
                                              Uri saveFileURI) {
        return getImagePickerIntent(1, 1, outputX, outputY, true, fromFileURI, saveFileURI);
    }

    /**
     * 获�?�[跳转至相册选择界�?�,并跳转至�?剪界�?�，默认�?�缩放�?剪区域]的Intent
     */
    public static Intent getImagePickerIntent(int aspectX, int aspectY, int outputX, int outputY, Uri fromFileURI,
                                              Uri saveFileURI) {
        return getImagePickerIntent(aspectX, aspectY, outputX, outputY, true, fromFileURI, saveFileURI);
    }

    /**
     * 获�?�[跳转至相册选择界�?�,并跳转至�?剪界�?�，�?�以指定是�?�缩放�?剪区域]的Intent
     *
     * @param aspectX     �?剪框尺寸比例X
     * @param aspectY     �?剪框尺寸比例Y
     * @param outputX     输出尺寸宽度
     * @param outputY     输出尺寸高度
     * @param canScale    是�?��?�缩放
     * @param fromFileURI 文件�?��?路径URI
     * @param saveFileURI 输出文件路径URI
     */
    public static Intent getImagePickerIntent(int aspectX, int aspectY, int outputX, int outputY, boolean canScale,
                                              Uri fromFileURI, Uri saveFileURI) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(fromFileURI, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", aspectX <= 0 ? 1 : aspectX);
        intent.putExtra("aspectY", aspectY <= 0 ? 1 : aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", canScale);
        // 图片剪�?�?足黑边解决
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, saveFileURI);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 去除人脸识别
        return intent.putExtra("noFaceDetection", true);
    }

    /**
     * 获�?�[跳转至相册选择界�?�,并跳转至�?剪界�?�，默认�?�缩放�?剪区域]的Intent
     */
    public static Intent getCameraIntent(Uri saveFileURI) {
        Intent mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        return mIntent.putExtra(MediaStore.EXTRA_OUTPUT, saveFileURI);
    }

    /**
     * 获�?�[跳转至�?剪界�?�,默认�?�缩放]的Intent
     */
    public static Intent getCropImageIntent(int outputX, int outputY, Uri fromFileURI,
                                            Uri saveFileURI) {
        return getCropImageIntent(1, 1, outputX, outputY, true, fromFileURI, saveFileURI);
    }

    /**
     * 获�?�[跳转至�?剪界�?�,默认�?�缩放]的Intent
     */
    public static Intent getCropImageIntent(int aspectX, int aspectY, int outputX, int outputY, Uri fromFileURI,
                                            Uri saveFileURI) {
        return getCropImageIntent(aspectX, aspectY, outputX, outputY, true, fromFileURI, saveFileURI);
    }


    /**
     * 获�?�[跳转至�?剪界�?�]的Intent
     */
    public static Intent getCropImageIntent(int aspectX, int aspectY, int outputX, int outputY, boolean canScale,
                                            Uri fromFileURI, Uri saveFileURI) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(fromFileURI, "image/*");
        intent.putExtra("crop", "true");
        // X方�?�上的比例
        intent.putExtra("aspectX", aspectX <= 0 ? 1 : aspectX);
        // Y方�?�上的比例
        intent.putExtra("aspectY", aspectY <= 0 ? 1 : aspectY);
        intent.putExtra("outputX", outputX);
        intent.putExtra("outputY", outputY);
        intent.putExtra("scale", canScale);
        // 图片剪�?�?足黑边解决
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("return-data", false);
        // 需�?将读�?�的文件路径和�?剪写入的路径区分，�?�则会造�?文件0byte
        intent.putExtra(MediaStore.EXTRA_OUTPUT, saveFileURI);
        // true-->返回数�?�类型�?�以设置为Bitmap，但是�?能传输太大，截大图用URI，�?图用Bitmap或者全部使用URI
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // �?�消人脸识别功能
        intent.putExtra("noFaceDetection", true);
        return intent;
    }

    /**
     * 获得选中相册的图片
     *
     * @param context 上下文
     * @param data    onActivityResult返回的Intent
     * @return bitmap
     */
    public static Bitmap getChoosedImage(Activity context, Intent data) {
        if (data == null) return null;
        Bitmap bm = null;
        ContentResolver cr = context.getContentResolver();
        Uri originalUri = data.getData();
        try {
            bm = MediaStore.Images.Media.getBitmap(cr, originalUri);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bm;
    }

    /**
     * 获得选中相册的图片路径
     *
     * @param context 上下文
     * @param data    onActivityResult返回的Intent
     * @return
     */
    public static String getChoosedImagePath(Activity context, Intent data) {
        if (data == null) return null;
        String path = "";
        ContentResolver resolver = context.getContentResolver();
        Uri originalUri = data.getData();
        if (null == originalUri) return null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = resolver.query(originalUri, projection, null, null, null);
        if (null != cursor) {
            try {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                path = cursor.getString(column_index);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return RxDataTool.isNullString(path) ? originalUri.getPath() : null;
    }

    /**
     * 获�?��?照之�?�的照片文件（JPG格�?）
     *
     * @param data     onActivityResult回调返回的数�?�
     * @param filePath 文件路径
     * @return 文件
     */
    public static File getTakePictureFile(Intent data, String filePath) {
        if (data == null) return null;
        Bundle extras = data.getExtras();
        if (extras == null) return null;
        Bitmap photo = extras.getParcelable("data");
        File file = new File(filePath);
        if (RxImageTool.save(photo, file, Bitmap.CompressFormat.JPEG)) return file;
        return null;
    }
}

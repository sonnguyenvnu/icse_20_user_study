package com.vondear.rxtool;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.vondear.rxtool.view.RxToast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.vondear.rxtool.RxFileTool.getDataColumn;
import static com.vondear.rxtool.RxFileTool.isDownloadsDocument;
import static com.vondear.rxtool.RxFileTool.isExternalStorageDocument;
import static com.vondear.rxtool.RxFileTool.isGooglePhotosUri;
import static com.vondear.rxtool.RxFileTool.isMediaDocument;

/**
 *
 * @author vondear
 * @date 2016/1/24
 */

public class RxPhotoTool {
    public static final int GET_IMAGE_BY_CAMERA = 5001;
    public static final int GET_IMAGE_FROM_PHONE = 5002;
    public static final int CROP_IMAGE = 5003;
    public static Uri imageUriFromCamera;
    public static Uri cropImageUri;

    public static void openCameraImage(final Activity activity) {
        imageUriFromCamera = createImagePathUri(activity);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // MediaStore.EXTRA_OUTPUT�?�数�?设置时,系统会自动生�?一个uri,但是�?�会返回一个缩略图
        // 返回图片在onActivityResult中通过以下代�?获�?�
        // Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
        activity.startActivityForResult(intent, GET_IMAGE_BY_CAMERA);
    }

    public static void openCameraImage(final Fragment fragment) {
        imageUriFromCamera = createImagePathUri(fragment.getContext());

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // MediaStore.EXTRA_OUTPUT�?�数�?设置时,系统会自动生�?一个uri,但是�?�会返回一个缩略图
        // 返回图片在onActivityResult中通过以下代�?获�?�
        // Bitmap bitmap = (Bitmap) data.getExtras().get("data");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUriFromCamera);
        fragment.startActivityForResult(intent, GET_IMAGE_BY_CAMERA);
    }

    public static void openLocalImage(final Activity activity) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        activity.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
    }

    public static void openLocalImage(final Fragment fragment) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        fragment.startActivityForResult(intent, GET_IMAGE_FROM_PHONE);
    }

    public static void cropImage(Activity activity, Uri srcUri) {
        cropImageUri = createImagePathUri(activity);

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(srcUri, "image/*");
        intent.putExtra("crop", "true");

        ////////////////////////////////////////////////////////////////
        // 1.宽高和比例都�?设置时,�?剪框�?�以自行调整(比例和大�?都�?�以�?�?调整)
        ////////////////////////////////////////////////////////////////
        // 2.�?�设置�?剪框宽高比(aspect)�?�,�?剪框比例固定�?�?�调整,�?�能调整大�?
        ////////////////////////////////////////////////////////////////
        // 3.�?剪�?�生�?图片宽高(output)的设置和�?剪框无关,�?�决定最终生�?图片大�?
        ////////////////////////////////////////////////////////////////
        // 4.�?剪框宽高比例(aspect)�?�以和�?剪�?�生�?图片比例(output)�?�?�,此时,
        //	会以�?剪框的宽为准,按照�?剪宽高比例生�?一个图片,该图和框选部分�?�能�?�?�,
        //  �?�?�的情况�?�能是截�?�框选的一部分,也�?�能超出框选部分,�?�下延伸补足
        ////////////////////////////////////////////////////////////////

        // aspectX aspectY 是�?剪框宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是�?剪�?�生�?图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);

        // return-data为true时,会直接返回bitmap数�?�,但是大图�?剪时会出现问题,推�??下�?�为false时的方�?
        // return-data为false时,�?会返回bitmap,但需�?指定一个MediaStore.EXTRA_OUTPUT�?存图片uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("return-data", true);

        activity.startActivityForResult(intent, CROP_IMAGE);
    }

    public static void cropImage(Fragment fragment, Uri srcUri) {
        cropImageUri = createImagePathUri(fragment.getContext());

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(srcUri, "image/*");
        intent.putExtra("crop", "true");

        ////////////////////////////////////////////////////////////////
        // 1.宽高和比例都�?设置时,�?剪框�?�以自行调整(比例和大�?都�?�以�?�?调整)
        ////////////////////////////////////////////////////////////////
        // 2.�?�设置�?剪框宽高比(aspect)�?�,�?剪框比例固定�?�?�调整,�?�能调整大�?
        ////////////////////////////////////////////////////////////////
        // 3.�?剪�?�生�?图片宽高(output)的设置和�?剪框无关,�?�决定最终生�?图片大�?
        ////////////////////////////////////////////////////////////////
        // 4.�?剪框宽高比例(aspect)�?�以和�?剪�?�生�?图片比例(output)�?�?�,此时,
        //	会以�?剪框的宽为准,按照�?剪宽高比例生�?一个图片,该图和框选部分�?�能�?�?�,
        //  �?�?�的情况�?�能是截�?�框选的一部分,也�?�能超出框选部分,�?�下延伸补足
        ////////////////////////////////////////////////////////////////

        // aspectX aspectY 是�?剪框宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是�?剪�?�生�?图片的宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);

        // return-data为true时,会直接返回bitmap数�?�,但是大图�?剪时会出现问题,推�??下�?�为false时的方�?
        // return-data为false时,�?会返回bitmap,但需�?指定一个MediaStore.EXTRA_OUTPUT�?存图片uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cropImageUri);
        intent.putExtra("return-data", true);

        fragment.startActivityForResult(intent, CROP_IMAGE);
    }

    /**
     * 创建一�?�图片地�?�uri,用于�?存�?照�?�的照片
     *
     * @param context
     * @return 图片的uri
     */
    public static Uri createImagePathUri(final Context context) {
        final Uri[] imageFilePath = {null};

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            imageFilePath[0] = Uri.parse("");
            RxToast.error("请先获�?�写入SDCard�?��?");
        } else {
            String status = Environment.getExternalStorageState();
            SimpleDateFormat timeFormatter = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA);
            long time = System.currentTimeMillis();
            String imageName = timeFormatter.format(new Date(time));
            // ContentValues是我们希望这�?�记录被创建时包�?�的数�?�信�?�
            ContentValues values = new ContentValues(3);
            values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
            values.put(MediaStore.Images.Media.DATE_TAKEN, time);
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");

            if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是�?�有SD�?�,优先使用SD�?�存储,当没有SD�?�时使用手机存储
                imageFilePath[0] = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                imageFilePath[0] = context.getContentResolver().insert(MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
            }
        }

        Log.i("", "生�?的照片输出路径：" + imageFilePath[0].toString());
        return imageFilePath[0];
    }


    //此方法 �?�能用于4.4以下的版本
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) {
            return null;
        }
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            String[] projection = {MediaStore.Images.ImageColumns.DATA};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

//            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


    /**
     * 根�?�Uri获�?�图片�?对路径，解决Android4.4以上版本Uri转�?�
     *
     * @param context
     * @param imageUri
     * @author yaoxing
     * @date 2014-10-12
     */
    @TargetApi(19)
    public static String getImageAbsolutePath(Context context, Uri imageUri) {
        if (context == null || imageUri == null) {
            return null;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
            if (isExternalStorageDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            } else if (isDownloadsDocument(imageUri)) {
                String id = DocumentsContract.getDocumentId(imageUri);
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(imageUri)) {
                String docId = DocumentsContract.getDocumentId(imageUri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = new String[]{split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } // MediaStore (and general)
        else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
            // Return the remote address
            if (isGooglePhotosUri(imageUri)) {
                return imageUri.getLastPathSegment();
            }
            return getDataColumn(context, imageUri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
            return imageUri.getPath();
        }
        return null;
    }


}

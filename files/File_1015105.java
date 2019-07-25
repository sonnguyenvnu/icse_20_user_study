package com.lqr.emoji;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * CSDN_LQR
 * 表情库Kit
 */

public class LQREmotionKit {

    private static String STICKER_NAME_IN_ASSETS = "sticker";
    private static Context mContext;
    private static float density;
    private static float scaleDensity;
    private static String STICKER_PATH;//默认路径在 /data/data/包�??/files/sticker 下
    private static IImageLoader imageLoader;

    private static void getAndSaveParameter(Context context) {
        mContext = context.getApplicationContext();

        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        density = dm.density;
        scaleDensity = dm.scaledDensity;
    }

    public static void init(Context context, String stickerPath) {
        getAndSaveParameter(context);
        STICKER_PATH = stickerPath;

        //将asset/sticker目录下默认的贴图�?制到STICKER_PATH下
        copyStickerToStickerPath(STICKER_NAME_IN_ASSETS);
    }

    public static void init(Context context) {
        init(context, new File(context.getFilesDir(), STICKER_NAME_IN_ASSETS).getAbsolutePath());
    }

    public static void init(Context context, IImageLoader imageLoader) {
        init(context);
        setImageLoader(imageLoader);
    }

    public static void init(Context context, String stickerPath, IImageLoader imageLoader) {
        init(context, stickerPath);
        setImageLoader(imageLoader);
    }

    private static void copyStickerToStickerPath(String assetsFolderPath) {
        AssetManager assetManager = mContext.getResources().getAssets();
        List<String> srcFile = new ArrayList<>();
        try {
            String[] stickers = assetManager.list(assetsFolderPath);
            for (String fileName : stickers) {
                if (!new File(LQREmotionKit.getStickerPath(), fileName).exists()) {
                    srcFile.add(fileName);
                }
            }
            if (srcFile.size() > 0) {
                copyToStickerPath(assetsFolderPath, srcFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyToStickerPath(final String assetsFolderPath, final List<String> srcFile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AssetManager assetManager = mContext.getResources().getAssets();
                for (String fileName : srcFile) {

                    if (fileName.contains(".")) {//文件
                        InputStream is = null;
                        FileOutputStream fos = null;
                        try {
                            is = assetManager.open(assetsFolderPath + File.separator + fileName);
                            File destinationFile;
                            if (assetsFolderPath.startsWith(STICKER_NAME_IN_ASSETS + File.separator)) {//递归回�?�的时候assetsFolderPath�?�能�?�为"sticker/tsj"
                                destinationFile = new File(getStickerPath(), assetsFolderPath.substring(assetsFolderPath.indexOf(File.separator) + 1) + File.separator + fileName);
                            } else {
                                destinationFile = new File(getStickerPath(), fileName);
                            }
                            fos = new FileOutputStream(destinationFile);
                            byte[] buffer = new byte[1024];
                            int len;
                            while ((len = is.read(buffer)) != -1) {
                                fos.write(buffer, 0, len);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            if (is != null) {
                                try {
                                    is.close();
                                    is = null;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    is = null;
                                }
                            }
                            if (fos != null) {
                                try {
                                    fos.close();
                                    fos = null;
                                } catch (IOException e) {
                                    e.printStackTrace();
                                    fos = null;
                                }
                            }
                        }
                    } else {//文件夹

                        File dir = new File(getStickerPath(), fileName);
                        if (!dir.exists()) {
                            dir.mkdirs();
                        }

                        copyStickerToStickerPath(assetsFolderPath + File.separator + fileName);
                    }
                }
            }
        }).start();
    }

    public static Context getContext() {
        return mContext;
    }

    public static String getStickerPath() {
        return STICKER_PATH;
    }

    public static IImageLoader getImageLoader() {
        if (imageLoader == null) {
            throw new RuntimeException("you should use setImageLoader() in your App onCreate()");
        }
        return imageLoader;
    }

    public static void setImageLoader(IImageLoader imageLoader) {
        LQREmotionKit.imageLoader = imageLoader;
    }

//    public static void setStickerPath(String stickerPath) {
//        STICKER_PATH = stickerPath;
//    }

    public static int dip2px(float dipValue) {
        return (int) (dipValue * density + 0.5f);
    }

    public static int px2dip(float pxValue) {
        return (int) (pxValue / density + 0.5f);
    }

    public static int sp2px(float spValue) {
        return (int) (spValue * scaleDensity + 0.5f);
    }
}

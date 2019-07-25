package com.cg.baseproject.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import com.cg.baseproject.BaseApplication;
import com.cg.baseproject.service.DownloadService;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static com.cg.baseproject.utils.ValidateUtils.convertStreamToString;

/**
 * @Description:主�?功能:文件管�?�
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 15:30
 * @Copyright: 个人版�?�所有
 * @Company:
 * @version: 1.0.0
 */

public class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static final String TAG = "FileUtils";

    private static final String ROOT_PATH = Environment.getExternalStorageDirectory()
            + File.separator
            + BaseApplication.getContext().getPackageName();

    private static final String CACHE = "/cache/";

    /**
     * 在�?始化时创建APP所需�?的基础文件夹
     * 在6.0以上版本时需�?进行�?��?申请
     * @param context 上下文
     */
    public static void init(Context context) {
//        LogUtils.d(TAG, "Root path is " + ROOT_PATH);
        createFileDir(context, CACHE);
    }
    
    /*
     * 创建目录
     *
     * @param context
     * @param dirName 文件夹�??称
     * @return
     */
    public static File createFileDir(Context context, String dirName) {
        String filePath;
        // 如SD�?�已存在，则存储；�??之存在data目录下
        if (isMountedSDCard()) {
            // SD�?�路径
            filePath = Environment.getExternalStorageDirectory() + File.separator + dirName;
        } else {
            filePath = context.getCacheDir().getPath() + File.separator + dirName;
        }
        File destDir = new File(filePath);
        if (!destDir.exists()) {
            boolean isCreate = destDir.mkdirs();
            Log.i("FileUtils", filePath + " has created. " + isCreate);
        }
        return destDir;
    }

    /**
     * 删除文件（若为目录，则递归删除�?目录和文件）
     *
     * @param file
     * @param delThisPath true代表删除�?�数指定file，false代表�?留�?�数指定file
     */
    public static void delFile(File file, boolean delThisPath) {
        if (!file.exists()) {
            return;
        }
        if (file.isDirectory()) {
            File[] subFiles = file.listFiles();
            if (subFiles != null) {
                int num = subFiles.length;
                // 删除�?目录和文件
                for (int i = 0; i < num; i++) {
                    delFile(subFiles[i], true);
                }
            }
        }
        if (delThisPath) {
            file.delete();
        }
    }

    /**
     * 获�?�文件大�?，�?��?为byte（若为目录，则包括所有�?目录和文件）
     *
     * @param file
     * @return
     */
    public static long getFileSize(File file) {
        long size = 0;
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] subFiles = file.listFiles();
                if (subFiles != null) {
                    int num = subFiles.length;
                    for (int i = 0; i < num; i++) {
                        size += getFileSize(subFiles[i]);
                    }
                }
            } else {
                size += file.length();
            }
        }
        return size;
    }

    public static File saveBitmapFile(Context context,String dirName, String fileName, Bitmap bitmap) {
        File file = new File(createFileDir(context,dirName), fileName);
        if (bitmap == null) {
            return null;
        }
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
    
    /**
     * �?存Bitmap到指定目录
     *
     * @param dir      目录
     * @param fileName 文件�??
     * @param bitmap
     * @throws IOException
     */
    public static void saveBitmap(File dir, String fileName, Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        File file = new File(dir, fileName);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断�?目录下文件是�?�存在
     *
     * @param dir      目录
     * @param fileName 文件�??
     * @return
     */
    public static boolean isFileExists(File dir, String fileName) {
        return new File(dir, fileName).exists();
    }



    /**
     * 检查是�?�已挂载SD�?�镜�?（是�?�存在SD�?�）
     *
     * @return
     */
    public static boolean isMountedSDCard() {
        if (Environment.MEDIA_MOUNTED.equals(Environment
            .getExternalStorageState())) {
            return true;
        } else {
            Log.e(TAG, "SDCARD is not MOUNTED !");
            return false;
        }
    }

    /**
     * 获�?�SD�?�剩余容�?（�?��?Byte）
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long gainSDFreeSize() {
        if (isMountedSDCard()) {
            // �?�得SD�?�文件路径
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());
            // 获�?��?�个数�?��?�的大�?(Byte)
            long blockSize = sf.getBlockSize();
            // 空闲的数�?��?�的数�?
            long freeBlocks = sf.getAvailableBlocks();

            // 返回SD�?�空闲大�?
            return freeBlocks * blockSize; // �?��?Byte
        } else {
            return 0;
        }
    }

    /**
     * 获�?�SD�?�总容�?（�?��?Byte）
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long gainSDAllSize() {
        if (isMountedSDCard()) {
            // �?�得SD�?�文件路径
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());
            // 获�?��?�个数�?��?�的大�?(Byte)
            long blockSize = sf.getBlockSize();
            // 获�?�所有数�?��?�数
            long allBlocks = sf.getBlockCount();
            // 返回SD�?�大�?（Byte）
            return allBlocks * blockSize;
        } else {
            return 0;
        }
    }

    /**
     * 获�?��?�用的SD�?�路径（若SD�?��?没有挂载则返回""）
     *
     * @return
     */

    public static String gainSDCardPath() {
        if (isMountedSDCard()) {
            File sdcardDir = Environment.getExternalStorageDirectory();
            if (!sdcardDir.canWrite()) {
                Log.w(TAG, "SDCARD can not write !");
            }
            return sdcardDir.getPath();
        }
        return "";
    }

    /**
     * 以行为�?��?读�?�文件内容，一次读一整行，常用于读�?��?�行的格�?化文件
     *
     * @param filePath
     *            文件路径
     */
    public static String readFileByLines(String filePath) throws IOException {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath),
                System.getProperty("file.encoding")));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
                sb.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return sb.toString();

    }

    /**
     * 以行为�?��?读�?�文件内容，一次读一整行，常用于读�?��?�行的格�?化文件
     *
     * @param filePath
     *            文件路径
     * @param encoding
     *            写文件编�?
     */
    public static String readFileByLines(String filePath, String encoding)
        throws IOException {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(filePath), encoding));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
                sb.append("\n");
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return sb.toString();
    }

    /**
     * �?存内容
     *
     * @param filePath
     *            文件路径
     * @param content
     *            �?存的内容
     * @throws IOException
     */
    public static void saveToFile(String filePath, String content)
        throws IOException {
        saveToFile(filePath, content, System.getProperty("file.encoding"));
    }

    /**
     * 指定编�?�?存内容
     *
     * @param filePath
     *            文件路径
     * @param content
     *            �?存的内容
     * @param encoding
     *            写文件编�?
     * @throws IOException
     */
    public static void saveToFile(String filePath, String content,
        String encoding) throws IOException {
        BufferedWriter writer = null;
        File file = new File(filePath);
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, false), encoding));
            writer.write(content);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 追加文本
     *
     * @param content
     *            需�?追加的内容
     * @param file
     *            待追加文件�?
     * @throws IOException
     */
    public static void appendToFile(String content, File file)
        throws IOException {
        appendToFile(content, file, System.getProperty("file.encoding"));
    }

    /**
     * 追加文本
     *
     * @param content
     *            需�?追加的内容
     * @param file
     *            待追加文件�?
     * @param encoding
     *            文件编�?
     * @throws IOException
     */
    public static void appendToFile(String content, File file, String encoding)
        throws IOException {
        BufferedWriter writer = null;
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, true), encoding));
            writer.write(content);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 判断文件是�?�存在
     *
     * @param filePath
     *            文件路径
     * @return 是�?�存在
     * @throws Exception
     */
    public static Boolean isExsit(String filePath) {
        Boolean flag = false;
        try {
            File file = new File(filePath);
            if (file.exists()) {
                flag = true;
            }
        } catch (Exception e) {
            Log.e(TAG, "判断文件失败-->"+e.getMessage());
        }

        return flag;
    }

    /**
     * 快速读�?�程�?应用包下的文件内容
     *
     * @param context
     *            上下文
     * @param filename
     *            文件�??称
     * @return 文件内容
     * @throws IOException
     */
    public static String read(Context context, String filename)
        throws IOException {
        FileInputStream inStream = context.openFileInput(filename);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        return new String(data);
    }

    /**
     * 读�?�指定目录文件的文件内容
     *
     * @param fileName
     *            文件�??称
     * @return 文件内容
     * @throws Exception
     */
    @SuppressWarnings("resource")
    public static String read(String fileName) throws IOException {
        FileInputStream inStream = new FileInputStream(fileName);
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();
        return new String(data);
    }

    /***
     * 以行为�?��?读�?�文件内容，一次读一整行，常用于读�?��?�行的格�?化文件
     *
     * @param fileName
     *            文件�??称
     * @param encoding
     *            文件编�?
     * @return 字符串内容
     * @throws IOException
     */
    public static String read(String fileName, String encoding)
        throws IOException {
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(
                new FileInputStream(fileName), encoding));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                sb.append(tempString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }

        return sb.toString();
    }

    /**
     * 读�?�raw目录的文件内容
     *
     * @param context
     *            内容上下文
     * @param rawFileId
     *            raw文件�??id
     * @return
     */
    public static String readRawValue(Context context, int rawFileId) {
        String result = "";
        try {
            InputStream is = context.getResources().openRawResource(rawFileId);
            int len = is.available();
            byte[] buffer = new byte[len];
            is.read(buffer);
            result = new String(buffer, "UTF-8");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读�?�assets目录的文件内容
     *
     * @param context
     *            内容上下文
     * @param fileName
     *            文件�??称，包�?�扩展�??
     * @return
     */
    public static String readAssetsValue(Context context, String fileName) {
        String result = "";
        try {
            InputStream is = context.getResources().getAssets().open(fileName);
            int len = is.available();
            byte[] buffer = new byte[len];
            is.read(buffer);
            result = new String(buffer, "UTF-8");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读�?�assets目录的文件内容
     *
     * @param context
     *            内容上下文
     * @param fileName
     *            文件�??称，包�?�扩展�??
     * @return
     */
    public static List<String> readAssetsListValue(Context context,
        String fileName) {
        List<String> list = new ArrayList<String>();
        try {
            InputStream in = context.getResources().getAssets().open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(in,
                "UTF-8"));
            String str = null;
            while ((str = br.readLine()) != null) {
                list.add(str);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获�?�SharedPreferences文件内容
     *
     * @param context
     *            上下文
     * @param fileNameNoExt
     *            文件�??称（�?用带�?�缀�??）
     * @return
     */
    public static Map<String, ?> readShrePerface(Context context,
        String fileNameNoExt) {
        SharedPreferences preferences = context.getSharedPreferences(
            fileNameNoExt, Context.MODE_PRIVATE);
        return preferences.getAll();
    }

    /**
     * 写入SharedPreferences文件内容
     *
     * @param context
     *            上下文
     * @param fileNameNoExt
     *            文件�??称（�?用带�?�缀�??）
     * @param values
     *            需�?写入的数�?�Map(String,Boolean,Float,Long,Integer)
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void writeShrePerface(Context context, String fileNameNoExt,
        Map<String, ?> values) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(
                fileNameNoExt, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            for (Iterator iterator = values.entrySet().iterator(); iterator
                .hasNext();) {
                Map.Entry<String, ?> entry = (Map.Entry<String, ?>) iterator
                    .next();
                if (entry.getValue() instanceof String) {
                    editor.putString(entry.getKey(), (String) entry.getValue());
                } else if (entry.getValue() instanceof Boolean) {
                    editor.putBoolean(entry.getKey(),
                        (Boolean) entry.getValue());
                } else if (entry.getValue() instanceof Float) {
                    editor.putFloat(entry.getKey(), (Float) entry.getValue());
                } else if (entry.getValue() instanceof Long) {
                    editor.putLong(entry.getKey(), (Long) entry.getValue());
                } else if (entry.getValue() instanceof Integer) {
                    editor.putInt(entry.getKey(), (Integer) entry.getValue());
                }
            }
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入应用程�?包files目录下文件
     *
     * @param context
     *            上下文
     * @param fileName
     *            文件�??称
     * @param content
     *            文件内容
     */
    public static void write(Context context, String fileName, String content) {
        try {

            FileOutputStream outStream = context.openFileOutput(fileName,
                Context.MODE_PRIVATE);
            outStream.write(content.getBytes());
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入应用程�?包files目录下文件
     *
     * @param context
     *            上下文
     * @param fileName
     *            文件�??称
     * @param content
     *            文件内容
     */
    public static void write(Context context, String fileName, byte[] content) {
        try {

            FileOutputStream outStream = context.openFileOutput(fileName,
                Context.MODE_PRIVATE);
            outStream.write(content);
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 写入应用程�?包files目录下文件
     *
     * @param context
     *            上下文
     * @param fileName
     *            文件�??称
     * @param modeType
     *            文件写入模�?（Context.MODE_PRIVATE�?Context.MODE_APPEND�?Context.
     *            MODE_WORLD_READABLE�?Context.MODE_WORLD_WRITEABLE）
     * @param content
     *            文件内容
     */
    public static void write(Context context, String fileName, byte[] content,
        int modeType) {
        try {

            FileOutputStream outStream = context.openFileOutput(fileName,
                modeType);
            outStream.write(content);
            outStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 指定编�?将内容写入目标文件
     *
     * @param target
     *            目标文件
     * @param content
     *            文件内容
     * @param encoding
     *            写入文件编�?
     * @throws Exception
     */
    public static void write(File target, String content, String encoding)
        throws IOException {
        BufferedWriter writer = null;
        try {
            if (!target.getParentFile().exists()) {
                target.getParentFile().mkdirs();
            }
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(target, false), encoding));
            writer.write(content);

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 指定目录写入文件内容
     *
     * @param filePath
     *            文件路径+文件�??
     * @param content
     *            文件内容
     * @throws IOException
     */
    public static void write(String filePath, byte[] content)
        throws IOException {
        FileOutputStream fos = null;

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            fos.write(content);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 写入文件
     *
     * @param inputStream 下载文件的字节�?对象
     * @param filePath 文件的存放路径
     *            (带文件�??称)
     * @throws IOException
     */
    public static File write(InputStream inputStream, String filePath)
        throws IOException {
        OutputStream outputStream = null;
        // 在指定目录创建一个空文件并获�?�文件对象
        File mFile = new File(filePath);
        if (!mFile.getParentFile().exists())
            mFile.getParentFile().mkdirs();
        try {
            outputStream = new FileOutputStream(mFile);
            byte buffer[] = new byte[4 * 1024];
            int lenght = 0;
            while ((lenght = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, lenght);
            }
            outputStream.flush();
            return mFile;
        } catch (IOException e) {
            Log.e(TAG, "写入文件失败，原因：" + e.getMessage());
            throw e;
        } finally {
            try {
                inputStream.close();
                if (outputStream != null) {
                    outputStream.close();
                    outputStream = null;
                }

            } catch (IOException e) {
            }
        }
    }

    /**
     * 指定目录写入文件内容
     *
     * @param filePath
     *            文件路径+文件�??
     * @param bitmap
     *            文件内容
     * @throws IOException
     */
    public static void saveAsJPEG(Bitmap bitmap, String filePath)
        throws IOException {
        FileOutputStream fos = null;

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 指定目录写入文件内容
     *
     * @param filePath
     *            文件路径+文件�??
     * @param bitmap
     *            文件内容
     * @throws IOException
     */
    public static void saveAsPNG(Bitmap bitmap, String filePath)
        throws IOException {
        FileOutputStream fos = null;

        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
        } finally {
            if (fos != null) {
                fos.close();
            }
        }
    }

    /**
     * 将文件转�?字符串
     *
     * @param file
     *            文件
     * @return
     * @throws Exception
     */
    public static String getStringFromFile(File file) throws Exception {
        FileInputStream fin = new FileInputStream(file);
        String ret = convertStreamToString(fin);
        // Make sure you close all streams.
        fin.close();
        return ret;
    }

    /**
     * �?制文件
     * @param in
     * @param out
     */
    public static void copyFile(InputStream in, OutputStream out) {
        try {
            byte[] b = new byte[2 * 1024 * 1024]; //2M memory
            int len = -1;
            while ((len = in.read(b)) > 0) {
                out.write(b, 0, len);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            closeIO(in, out);
        }
    }

   

    /**
     *分享文件
     * @param context
     * @param title
     * @param filePath
     */
    public static void shareFile(Context context, String title, String filePath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.parse("file://" + filePath);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, title));
    }

    /**
     *压缩
     * @param is
     * @param os
     */
    public static void zip(InputStream is, OutputStream os) {
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(os);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                gzip.write(buf, 0, len);
                gzip.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            closeIO(is, gzip);
        }
    }

    /**
     *解压
     * @param is
     * @param os
     */
    public static void unzip(InputStream is, OutputStream os) {
        GZIPInputStream gzip = null;
        try {
            gzip = new GZIPInputStream(is);
            byte[] buf = new byte[1024];
            int len;
            while ((len = gzip.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            closeIO(gzip, os);
        }
    }

    /**
     * 格�?化文件大�?
     * @param context
     * @param size
     * @return
     */
    public static String formatFileSize(Context context, long size) {
        return Formatter.formatFileSize(context, size);
    }

    /**
     * 将输入�?写入到文件
     * @param is
     * @param file
     */
    public static void Stream2File(InputStream is, File file) {
        byte[] b = new byte[1024];
        int len;
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
//            closeIO(is, os);
        }
    }


    /**
     * 创建文件夹(支�?覆盖已存在的�?��??文件夹)
     * @param filePath
     * @param recreate
     * @return
     */
    public static boolean createFolder(String filePath, boolean recreate) {
        String folderName = getFolderName(filePath);
        if (folderName == null || folderName.length() == 0 || folderName.trim().length() == 0) {
            return false;
        }
        File folder = new File(folderName);
        if (folder.exists()) {
            if (recreate) {
                deleteFile(folderName);
                return folder.mkdirs();
            } else {
                return true;
            }
        } else {
            return folder.mkdirs();
        }
    }

    public static boolean deleteFile(String filename) {
        return new File(filename).delete();
    }

    /**
     * 获�?�文件�??
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        if (StringUtils.isEmpty(filePath)) {
            return filePath;
        }

        int filePosi = filePath.lastIndexOf(File.separator);
        return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
    }

    /**
     * �?命�??文件\文件夹
     * @param filepath
     * @param newName
     * @return
     */
    public static boolean rename(String filepath, String newName) {
        File file = new File(filepath);
        return file.exists() && file.renameTo(new File(newName));
    }

    /**
     * 获�?�文件夹�??称
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {
        if (filePath == null || filePath.length() == 0 || filePath.trim().length() == 0) {
            return filePath;
        }
        int filePos = filePath.lastIndexOf(File.separator);
        return (filePos == -1) ? "" : filePath.substring(0, filePos);
    }

    /**
     * 获�?�文件夹下所有文件
     * @param path
     * @return
     */
    public static ArrayList<File> getFilesArray(String path) {
        File file = new File(path);
        File files[] = file.listFiles();
        ArrayList<File> listFile = new ArrayList<File>();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    listFile.add(files[i]);
                }
                if (files[i].isDirectory()) {
                    listFile.addAll(getFilesArray(files[i].toString()));
                }
            }
        }
        return listFile;
    }

    /**
     * 打开图片
     * @param mContext
     * @param imagePath
     */
    public static void openImage(Context mContext, String imagePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(imagePath));
        intent.setDataAndType(uri, "image/*");
        mContext.startActivity(intent);
    }

    /**
     * 打开视频
     * @param mContext
     * @param videoPath
     */
    public static void openVideo(Context mContext, String videoPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(videoPath));
        intent.setDataAndType(uri, "video/*");
        mContext.startActivity(intent);
    }

    /**
     * 打开URL
     * @param mContext
     * @param url
     */
    public static void openURL(Context mContext, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }

    /**
     * 下载文件
     * @param context
     * @param fileurl
     */
    public static void downloadFile(Context context, String fileurl) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(fileurl));
        request.setDestinationInExternalPublicDir("/Download/", fileurl.substring(fileurl.lastIndexOf("/") + 1));
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadManager.enqueue(request);
    }

    /**
     * 通过APKURL�?�级应用
     * @param context
     * @param fileurl
     */
    public static void upgradeApp(Context context, String fileurl) {
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra("fileurl", fileurl);
        context.startService(intent);
    }

    /**
     * 在没有sdcard时获�?�内部存储路径
     * @return
     */
    public static String getInternalPath(Context context) {
//        LogUtils.d(TAG, "internal path is " + context.getFilesDir().getPath());
        return context.getFilesDir().getPath() + context.getPackageName();
    }

    /**
     * 检测是�?�SDCard是�?�存在
     * @return true：存在 false：�?存在
     */
    public static boolean isExistSDCard() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }


    /**
     * 删除目录下所有文件
     * @param Path    路径
     */
    public static void deleteAllFile(String Path) {

        // 删除目录下所有文件
        File path = new File(Path);
        File files[] = path.listFiles();
        if (files != null) {
            for (File tfi : files) {
                if (tfi.isDirectory()) {
                    System.out.println(tfi.getName());
                }
                else {
                    tfi.delete();
                }
            }
        }
    }

    /**
     * 删除方法, 这里�?�会删除�?个文件夹下的文件，如果传入的directory是个文件，将�?�?�处�?�
     * @param directory
     * @return void
     */
    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
                if(file.isDirectory())
                    deleteFilesByDirectory(file);
                file.delete();
            }
        }else{
            Log.i("", "This directory is file, not execute delete");
        }
    }
}

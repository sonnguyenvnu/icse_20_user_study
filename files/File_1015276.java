package info.xiaomo.core.untils;

import info.xiaomo.core.constant.FileConst;
import info.xiaomo.core.constant.SymbolConst;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 此类中�?装一些常用的文件�?作。
 * 所有方法都是�?��?方法，�?需�?生�?此类的实例，
 * 为�?��?生�?此类的实例，构造方法被申明为private类型的。
 *
 * @author : xiaomo
 * @since 1.0
 */

public class FileUtil {
    /**
     * Buffer size when reading from input stream.
     *
     * @since ostermillerutils 1.00.00
     */
    private final static int BUFFER_SIZE = 1024;


    /**
     * �?有构造方法，防止类的实例化，因为工具类�?需�?实例化。
     */
    private FileUtil() {

    }

    /**
     * 修改文件的最�?�访问时间。
     * 如果文件�?存在则创建该文件。
     * <b>目�?这个方法的行为方�?还�?稳定，主�?是方法有些信�?�输出，这些信�?�输出是�?��?留还在考虑中。</b>
     *
     * @param file 需�?修改最�?�访问时间的文件。
     * @since 1.0
     */
    public static void touch(File file) {
        long currentTime = System.currentTimeMillis();
        if (!file.exists()) {
            System.err.println("file not found:" + file.getName());
            System.err.println("Create a new file:" + file.getName());
            try {
                if (file.createNewFile()) {
                    System.out.println("Succeeded!");
                } else {
                    System.err.println("Create file failed!");
                }
            } catch (IOException e) {
                System.err.println("Create file failed!");
                e.printStackTrace();
            }
        }
        boolean result = file.setLastModified(currentTime);
        if (!result) {
            System.err.println("touch failed: " + file.getName());
        }
    }

    /**
     * 修改文件的最�?�访问时间。
     * 如果文件�?存在则创建该文件。
     * <b>目�?这个方法的行为方�?还�?稳定，主�?是方法有些信�?�输出，这些信�?�输出是�?��?留还在考虑中。</b>
     *
     * @param fileName 需�?修改最�?�访问时间的文件的文件�??。
     * @since 1.0
     */
    public static void touch(String fileName) {
        File file = new File(fileName);
        touch(file);
    }

    /**
     * 修改文件的最�?�访问时间。
     * 如果文件�?存在则创建该文件。
     * <b>目�?这个方法的行为方�?还�?稳定，主�?是方法有些信�?�输出，这些信�?�输出是�?��?留还在考虑中。</b>
     *
     * @param files 需�?修改最�?�访问时间的文件数组。
     * @since 1.0
     */
    public static void touch(File[] files) {
        for (File file : files) {
            touch(file);
        }
    }

    /**
     * 修改文件的最�?�访问时间。
     * 如果文件�?存在则创建该文件。
     * <b>目�?这个方法的行为方�?还�?稳定，主�?是方法有些信�?�输出，这些信�?�输出是�?��?留还在考虑中。</b>
     *
     * @param fileNames 需�?修改最�?�访问时间的文件�??数组。
     * @since 1.0
     */
    public static void touch(String[] fileNames) {
        File[] files = new File[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            files[i] = new File(fileNames[i]);
        }
        touch(files);
    }

    /**
     * 判断指定的文件是�?�存在。
     *
     * @param fileName �?判断的文件的文件�??
     * @return 存在时返回true，�?�则返回false。
     * @since 1.0
     */
    public static boolean isFileExist(String fileName) {
        return new File(fileName).isFile();
    }

    /**
     * 创建指定的目录。
     * 如果指定的目录的父目录�?存在则创建其目录书上所有需�?的父目录。
     * <b>注�?：�?�能会在返回false的时候创建部分父目录。</b>
     *
     * @param file �?创建的目录
     * @return 完全创建�?功时返回true，�?�则返回false。
     * @since 1.0
     */
    public static boolean makeDirectory(File file) {
        File parent = file.getParentFile();
        return parent != null && parent.mkdirs();
    }

    /**
     * 创建指定的目录。
     * 如果指定的目录的父目录�?存在则创建其目录书上所有需�?的父目录。
     * <b>注�?：�?�能会在返回false的时候创建部分父目录。</b>
     *
     * @param fileName �?创建的目录的目录�??
     * @return 完全创建�?功时返回true，�?�则返回false。
     * @since 1.0
     */
    public static boolean makeDirectory(String fileName) {
        File file = new File(fileName);
        return makeDirectory(file);
    }

    /**
     * 清空指定目录中的文件。
     * 这个方法将尽�?�能删除所有的文件，但是�?��?有一个文件没有被删除都会返回false。
     * �?�外这个方法�?会迭代删除，�?��?会删除�?目录�?�其内容。
     *
     * @param directory �?清空的目录
     * @return 目录下的所有文件都被�?功删除时返回true，�?�则返回false.
     * @since 1.0
     */
    public static boolean emptyDirectory(File directory) {
        boolean result = true;
        File[] entries = directory.listFiles();
        for (File entry : entries != null ? entries : new File[0]) {
            if (!entry.delete()) {
                result = false;
            }
        }
        return result;
    }

    /**
     * 清空指定目录中的文件。
     * 这个方法将尽�?�能删除所有的文件，但是�?��?有一个文件没有被删除都会返回false。
     * �?�外这个方法�?会迭代删除，�?��?会删除�?目录�?�其内容。
     *
     * @param directoryName �?清空的目录的目录�??
     * @return 目录下的所有文件都被�?功删除时返回true，�?�则返回false。
     * @since 1.0
     */
    public static boolean emptyDirectory(String directoryName) {
        File dir = new File(directoryName);
        return emptyDirectory(dir);
    }

    /**
     * 删除指定目录�?�其中的所有内容。
     *
     * @param dirName �?删除的目录的目录�??
     * @return 删除�?功时返回true，�?�则返回false。
     * @since 1.0
     */
    public static boolean deleteDirectory(String dirName) {
        return deleteDirectory(new File(dirName));
    }

    /**
     * 删除指定目录�?�其中的所有内容。
     *
     * @param dir �?删除的目录
     * @return 删除�?功时返回true，�?�则返回false。
     * @since 1.0
     */
    public static boolean deleteDirectory(File dir) {
        if ((dir == null) || !dir.isDirectory()) {
            throw new IllegalArgumentException("Argument " + dir +
                    " is not a directory. ");
        }

        File[] entries = dir.listFiles();
        int sz = entries != null ? entries.length : 0;

        for (File entry : entries != null ? entries : new File[0]) {
            if (entry.isDirectory()) {
                if (!deleteDirectory(entry)) {
                    return false;
                }
            } else {
                if (!entry.delete()) {
                    return false;
                }
            }
        }

        return dir.delete();
    }


    /**
     * 列出目录中的所有内容，包括其�?目录中的内容。
     *
     * @param file   �?列出的目录
     * @param filter 过滤器
     * @return 目录内容的文件数组。
     * @since 1.0
     */
    public static File[] listAll(File file,
                                 javax.swing.filechooser.FileFilter filter) {
        List<File> arrayList = new ArrayList<>();
        File[] files;
        if (!file.exists() || file.isFile()) {
            return null;
        }
        list(arrayList, file, filter);
        files = new File[arrayList.size()];
        arrayList.toArray(files);
        return files;
    }


    /**
     * 返回文件的URL地�?�。
     *
     * @param file 文件
     * @return 文件对应的的URL地�?�
     * @throws MalformedURLException
     * @since 1.0
     * @deprecated 在实现的时候没有注�?到File类本身带一个toURL方法将文件路径转�?�为URL。
     * 请使用File.toURL方法。
     */
    public static URL getURL(File file) throws MalformedURLException {
        String fileURL = "file:/" + file.getAbsolutePath();
        return new URL(fileURL);
    }

    /**
     * 从文件路径得到文件�??。
     *
     * @param filePath 文件的路径，�?�以是相对路径也�?�以是�?对路径
     * @return 对应的文件�??
     * @since 1.0
     */
    public static String getFileName(String filePath) {
        File file = new File(filePath);
        return file.getName();
    }

    /**
     * 从文件�??得到文件�?对路径。
     *
     * @param fileName 文件�??
     * @return 对应的文件路径
     * @since 1.0
     */
    public static String getFilePath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    /**
     * 将DOS/Windows格�?的路径转�?�为UNIX/Linux格�?的路径。
     * 其实就是将路径中的"\"全部�?�为"/"，因为在�?些情况下我们转�?�为这�?方�?比较方便，
     * �?中程度上说"/"比"\"更适�?�作为路径分隔符，而且DOS/Windows也将它当作路径分隔符。
     *
     * @param filePath 转�?��?的路径
     * @return 转�?��?�的路径
     * @since 1.0
     */
    public static String toUNIXpath(String filePath) {
        return filePath.replace('\\', '/');
    }

    /**
     * 从文件�??得到UNIX风格的文件�?对路径。
     *
     * @param fileName 文件�??
     * @return 对应的UNIX风格的文件路径
     * @see #toUNIXpath(String filePath) toUNIXpath
     * @since 1.0
     */
    public static String getUNIXfilePath(String fileName) {
        File file = new File(fileName);
        return toUNIXpath(file.getAbsolutePath());
    }

    /**
     * 得到文件的类型。
     * 实际上就是得到文件�??中最�?�一个“.�?�?��?�的部分。
     *
     * @param fileName 文件�??
     * @return 文件�??中的类型部分
     * @since 1.0
     */
    public static String getFileType(String fileName) {
        int point = fileName.lastIndexOf('.');
        int length = fileName.length();
        if (point == -1 || point == length - 1) {
            return "";
        } else {
            return fileName.substring(point + 1, length);
        }
    }

    /**
     * 得到文件的类型。
     * 实际上就是得到文件�??中最�?�一个“.�?�?��?�的部分。
     *
     * @param file 文件
     * @return 文件�??中的类型部分
     * @since 1.0
     */
    public static String getFileType(File file) {
        return getFileType(file.getName());
    }

    /**
     * 得到文件的�??字部分。
     * 实际上就是路径中的最�?�一个路径分隔符�?�的部分。
     *
     * @param fileName 文件�??
     * @return 文件�??中的�??字部分
     * @since 1.0
     */
    public static String getNamePart(String fileName) {
        int point = getPathLsatIndex(fileName);
        int length = fileName.length();
        if (point == -1) {
            return fileName;
        } else if (point == length - 1) {
            int secondPoint = getPathLsatIndex(fileName, point - 1);
            if (secondPoint == -1) {
                if (length == 1) {
                    return fileName;
                } else {
                    return fileName.substring(0, point);
                }
            } else {
                return fileName.substring(secondPoint + 1, point);
            }
        } else {
            return fileName.substring(point + 1);
        }
    }

    /**
     * 得到文件�??中的父路径部分。
     * 对两�?路径分隔符都有效。
     * �?存在时返回""。
     * 如果文件�??是以路径分隔符结尾的则�?考虑该分隔符，例如"/path/"返回""。
     *
     * @param fileName 文件�??
     * @return 父路径，�?存在或者已�?是父目录时返回""
     * @since 1.0
     */
    public static String getPathPart(String fileName) {
        int point = getPathLsatIndex(fileName);
        int length = fileName.length();
        if (point == -1) {
            return "";
        } else if (point == length - 1) {
            int secondPoint = getPathLsatIndex(fileName, point - 1);
            if (secondPoint == -1) {
                return "";
            } else {
                return fileName.substring(0, secondPoint);
            }
        } else {
            return fileName.substring(0, point);
        }
    }

    /**
     * 得到路径分隔符在文件路径中首次出现的�?置。
     * 对于DOS或者UNIX风格的分隔符都�?�以。
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中首次出现的�?置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathIndex(String fileName) {
        int point = fileName.indexOf('/');
        if (point == -1) {
            point = fileName.indexOf('\\');
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中指定�?置�?�首次出现的�?置。
     * 对于DOS或者UNIX风格的分隔符都�?�以。
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的�?置
     * @return 路径分隔符在路径中指定�?置�?�首次出现的�?置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathIndex(String fileName, int fromIndex) {
        int point = fileName.indexOf('/', fromIndex);
        if (point == -1) {
            point = fileName.indexOf('\\', fromIndex);
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中最�?�出现的�?置。
     * 对于DOS或者UNIX风格的分隔符都�?�以。
     *
     * @param fileName 文件路径
     * @return 路径分隔符在路径中最�?�出现的�?置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathLsatIndex(String fileName) {
        int point = fileName.lastIndexOf('/');
        if (point == -1) {
            point = fileName.lastIndexOf('\\');
        }
        return point;
    }

    /**
     * 得到路径分隔符在文件路径中指定�?置�?最�?�出现的�?置。
     * 对于DOS或者UNIX风格的分隔符都�?�以。
     *
     * @param fileName  文件路径
     * @param fromIndex 开始查找的�?置
     * @return 路径分隔符在路径中指定�?置�?最�?�出现的�?置，没有出现时返回-1。
     * @since 1.0
     */
    public static int getPathLsatIndex(String fileName, int fromIndex) {
        int point = fileName.lastIndexOf('/', fromIndex);
        if (point == -1) {
            point = fileName.lastIndexOf('\\', fromIndex);
        }
        return point;
    }

    /**
     * 将文件�??中的类型部分去掉。
     *
     * @param filename 文件�??
     * @return 去掉类型部分的结果
     * @since 1.0
     */
    public static String trimType(String filename) {
        int index = filename.lastIndexOf(".");
        if (index != -1) {
            return filename.substring(0, index);
        } else {
            return filename;
        }
    }

    /**
     * 得到相对路径。
     * 文件�??�?是目录�??的�?节点时返回文件�??。
     *
     * @param pathName 目录�??
     * @param fileName 文件�??
     * @return 得到文件�??相对于目录�??的相对路径，目录下�?存在该文件时返回文件�??
     * @since 1.0
     */
    public static String getSubpath(String pathName, String fileName) {
        int index = fileName.indexOf(pathName);
        if (index != -1) {
            return fileName.substring(index + pathName.length() + 1);
        } else {
            return fileName;
        }
    }

    /**
     * 检查给定目录的存在性
     * �?�?指定的路径�?�用，如果指定的路径�?存在，那么建立该路径，�?�以为多级路径
     *
     * @param path
     * @return 真�?�值
     * @since 1.0
     */
    public static boolean pathValidate(String path) {
        String[] arraypath = path.split("/");
        String tmppath = "";
        for (String anArraypath : arraypath) {
            tmppath += "/" + anArraypath;
            File d = new File(tmppath.substring(1));
            //检查Sub目录是�?�存在
            if (!d.exists()) {
                System.out.println(tmppath.substring(1));
                if (!d.mkdir()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 根�?�内容生�?文件
     *
     * @param path          �?生�?文件的�?对路径，
     * @param modulecontent 文件的内容。
     * @return 真�?�值
     * @since 1.0
     */
    public static boolean genModuleTpl(String path, String modulecontent) throws IOException {

        path = getUNIXfilePath(path);
        String[] patharray = path.split("\\/");
        String modulepath = "";
        for (int i = 0; i < patharray.length - 1; i++) {
            modulepath += "/" + patharray[i];
        }
        File d = new File(modulepath.substring(1));
        if (!d.exists()) {
            if (!pathValidate(modulepath.substring(1))) {
                return false;
            }
        }
        //建立FileWriter对象，并实例化fw
        FileWriter fw = new FileWriter(path);
        //将字符串写入文件
        fw.write(modulecontent);
        fw.close();
        return true;
    }

    /**
     * 获�?�图片文件的扩展�??（�?�布系统专用）
     *
     * @param picPath 为图片�??称加上�?�?�的路径�?包括扩展�??
     * @return 图片的扩展�??
     * @since 1.0
     */
    public static String getPicExtendName(String picPath) {
        picPath = getUNIXfilePath(picPath);
        String picExtend = "";
        String gif = ".gif";
        if (isFileExist(picPath + gif)) {
            picExtend = gif;
        }
        String jpeg = ".jpeg";
        if (isFileExist(picPath + jpeg)) {
            picExtend = jpeg;
        }
        String jpg = ".jpg";
        if (isFileExist(picPath + jpg)) {
            picExtend = jpg;
        }
        String png = ".png";
        if (isFileExist(picPath + png)) {
            picExtend = png;
        }
        //返回图片扩展�??
        return picExtend;
    }

    public static boolean copyFile(File in, File out) throws Exception {
        try {
            FileInputStream fis = new FileInputStream(in);
            FileOutputStream fos = new FileOutputStream(out);
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
            fis.close();
            fos.close();
            return true;
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }
    }

    public static boolean copyFile(String infile, String outfile) throws Exception {
        try {
            File in = new File(infile);
            File out = new File(outfile);
            return copyFile(in, out);
        } catch (IOException ie) {
            ie.printStackTrace();
            return false;
        }

    }

    /**
     * Copy the data from the input stream to the output stream.
     *
     * @param in  data source
     * @param out data destination
     * @throws IOException in an input or output error occurs
     * @since orientals 1.00.00
     */
    private static void copy(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    /**
     * 将目录中的内容添加到列表。
     *
     * @param list   文件列表
     * @param filter 过滤器
     * @param file   目录
     */
    private static void list(List<File> list, File file,
                             javax.swing.filechooser.FileFilter filter) {
        if (filter.accept(file)) {
            list.add(file);
            if (file.isFile()) {
                return;
            }
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files != null ? files : new File[0]) {
                list(list, file1, filter);
            }
        }

    }


    /**
     * 文件上传
     *
     * @param file  file
     * @param email email
     * @return fileUrl
     */
    public static String upload(MultipartFile file, String email) {
        String savePath = "";
        String filename = "";
        if (file != null && !file.isEmpty()) {
            // 获�?�图片的文件�??
            String fileName = file.getOriginalFilename();
            // �?新定义图片�??字
            filename = FileUtil.getNewFileName(fileName, email);
            //上传�?务器上 新文件路径
            String os = System.getProperty("os.name").toLowerCase();
            try {
                // 判断�?务器上 文件夹是�?�存在
                File newFile = new File(savePath);
                if (!newFile.exists()) {
                    boolean result = newFile.mkdirs();
                    System.out.println(result);
                }
                savePath = savePath + filename;
                FileOutputStream out = new FileOutputStream(savePath);
                // 写入文件
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return filename;
    }


    public static byte[] readStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outsStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = inStream.read(buffer)) != -1) {
            outsStream.write(buffer, 0, len);
        }
        outsStream.close();
        inStream.close();
        return outsStream.toByteArray();
    }

    public static byte[] readFileImage(File file) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(file));
        int len = bufferedInputStream.available();
        byte[] bytes = new byte[len];
        int r = bufferedInputStream.read(bytes);
        if (len != r) {
            bytes = null;
            throw new IOException("读�?�文件�?正确");
        }
        bufferedInputStream.close();
        return bytes;
    }

    public static byte[] readFileImage(String filename) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(filename));
        int len = bufferedInputStream.available();
        byte[] bytes = new byte[len];
        int r = bufferedInputStream.read(bytes);
        if (len != r) {
            bytes = null;
            throw new IOException("读�?�文件�?正确");
        }
        bufferedInputStream.close();
        return bytes;
    }


    /**
     * 读�?�返回的信�?�
     *
     * @param in
     * @return
     */
    private static String getData(InputStream in) {
        String result = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = "";
        try {
            while ((line = br.readLine()) != null) {
                // result = result + line;
                sb.append(line);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }


    /**
     * 获�?�文件内容
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    private static String getFile(String filePath) throws IOException {
        byte[] b = new byte[28];
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
            inputStream.read(b, 0, 28);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytesToHexString(b);
    }

    /**
     * @param filePath filePath
     * @return FileConst
     * @throws IOException
     */
    public static FileConst getType(String filePath) throws IOException {
        String fileHead = getFile(filePath);
        if (fileHead == null || fileHead.length() == 0) {
            return null;
        }
        fileHead = fileHead.toUpperCase();
        FileConst[] fileConsts = FileConst.values();
        for (FileConst type : fileConsts) {
            if (fileHead.startsWith(type.getValue())) {
                return type;
            }
        }
        return null;
    }

    public static String getNewFileName(String fileName, String email) {
        String fileType = FileUtil.getFileType(fileName);
        String newName = email.split(SymbolConst.AT)[0];
        return (TimeUtil.getDateNow(TimeUtil.DATE_FORMAT_STRING) + SymbolConst.HENGXIAN + newName + SymbolConst.DIAN + fileType).toLowerCase();
    }

    public static boolean isImage(String imageName) {
        String fileType = FileUtil.getFileType(imageName);
        return !("bmp".equals(fileType) || "BMP".equals(fileType)
                || "jpg".equals(fileType) || "JPG".equals(fileType)
                || "jpeg".equals(fileType) || "JPEG".equals(fileType)
                || "git".equals(fileType) || "GIF".equals(fileType)
                || "png".equals(fileType) || "PNG".equals(fileType));
    }

}

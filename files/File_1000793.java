package org.nutz.mvc.upload;

import java.util.regex.Pattern;

import org.nutz.filepool.FilePool;
import org.nutz.filepool.NutFilePool;
import org.nutz.filepool.SynchronizedFilePool;
import org.nutz.lang.Encoding;
import org.nutz.lang.Strings;
import org.nutz.lang.util.Regex;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * 执行上传时一些必�?的�?置信�?�
 * 
 * @author zozoh(zozohtnt@gmail.com)
 */
public class UploadingContext {
    
    private static final Log log = Logs.get();

    public static UploadingContext create(String poolPath) {
        return create(NutFilePool.getOrCreatePool(poolPath, 0));
    }

    public static UploadingContext create(FilePool pool) {
        return new UploadingContext(pool);
    }

    public UploadingContext(String poolPath) {
        this(NutFilePool.getOrCreatePool(poolPath, 2000));
    }

    public UploadingContext(FilePool pool) {
        charset = Encoding.UTF8;
        bufferSize = 8192;
        setFilePool(pool);
    }

    /**
     * 默认为 UTF-8，上传字节�?的编�?
     */
    private String charset;

    /**
     * 临时文件池
     */
    private FilePool filePool;

    /**
     * 缓冲，默认 8192
     */
    private int bufferSize;

    /**
     * 是�?�忽略空文件，默认为 false
     */
    private boolean ignoreNull;

    /**
     * 如果大于0，对于�?个上传的文件，都判断，如果超过了这个大�?，则拒�?继续上传
     * <p>
     * �?��?为字节
     */
    private int maxFileSize;

    /**
     * 一个正则表达�?，�??述了�?�以�?许的文件�??
     */
    private String nameFilter;

    /**
     * 一个正则表达�?，�??述了�?�以�?许的文件内容类型
     */
    private String contentTypeFilter;
    
    private Pattern nameFilterPattern;

    public String getCharset() {
        return charset;
    }

    public UploadingContext setCharset(String charset) {
        this.charset = charset;
        return this;
    }

    public FilePool getFilePool() {
        return filePool;
    }

    public UploadingContext setFilePool(FilePool pool) {
        if (!(pool instanceof SynchronizedFilePool))
            pool = new SynchronizedFilePool(pool);
        this.filePool = pool;
        return this;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public UploadingContext setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
        if (bufferSize < 128 && log.isWarnEnabled()) {
            log.warn("Uploading buffer is less than 128!! Auto-fix to 128!! 8192 will be much better!!");
            this.bufferSize = 128;
        }
        return this;
    }

    public boolean isIgnoreNull() {
        return ignoreNull;
    }

    public UploadingContext setIgnoreNull(boolean ignoreNull) {
        this.ignoreNull = ignoreNull;
        return this;
    }

    public int getMaxFileSize() {
        return maxFileSize;
    }

    public UploadingContext setMaxFileSize(int maxFileSize) {
        this.maxFileSize = maxFileSize;
        return this;
    }

    public String getNameFilter() {
        return nameFilter;
    }

    public UploadingContext setNameFilter(String nameFilter) {
        this.nameFilter = nameFilter;
        if (!Strings.isBlank(nameFilter))
        	this.nameFilterPattern = Pattern.compile(nameFilter);
        return this;
    }

    public boolean isNameAccepted(String name) {
        if (null == nameFilter || Strings.isBlank(name) 
                || "\"\"".equals(name)) //用户�?选择文件时,文件�??会是"" 两个�?�引�?�
            return true;
        if (nameFilterPattern == null)
        	return Regex.match(nameFilter, name.toLowerCase());
        return nameFilterPattern.matcher(name.toLowerCase()).find();
    }

    public String getContentTypeFilter() {
        return contentTypeFilter;
    }

    public UploadingContext setContentTypeFilter(String contentTypeFilter) {
        this.contentTypeFilter = contentTypeFilter;
        return this;
    }

    public boolean isContentTypeAccepted(String contentType) {
        if (null == contentTypeFilter || Strings.isBlank(contentType))
            return true;
        return Regex.match(contentTypeFilter, contentType.toLowerCase());
    }
}

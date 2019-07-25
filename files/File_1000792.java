package org.nutz.mvc.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.nutz.lang.Files;

/**
 * �?装上传文件的信�?�
 * <p> 1.r.55开始使用与servlet 3.0+一致的Part接�?�,原方法标记为弃用. </p>
 */
public class TempFile {

    private File file;
    private FieldMeta meta;

    public TempFile(FieldMeta meta, File f) {
        this.meta = meta;
        this.file = f;
    }

    /**
     * 建议使用 getInputStream替代
     */
    public File getFile() {
        return file;
    }

    /**
     * 元数�?�
     * @return 该文件的元数�?�
     */
    @Deprecated
    public FieldMeta getMeta() {
        return meta;
    }
    
    /**
     * 数�?��?,务必自行关闭
     */
    public InputStream getInputStream() throws IOException {
    	return new FileInputStream(file);
    }
    
    /**
     * 文件类型
     */
    public String getContentType() {
    	return meta.getContentType();
    }
    
    /**
     * 表�?��??称
     */
    public String getName() {
    	return meta.getName();
    }
    
    /**
     * 本地文件�??
     */
    public String getSubmittedFileName() {
    	return meta.getFileLocalName();
    }
    
    /**
     * 文件大�?
     */
    public long getSize() {
    	return file.length();
    }
    
    /**
     * 写入目标文件
     */
    public void write(String fileName) throws IOException {
    	Files.copy(file, new File(fileName));
    }
    
    /**
     * 删除临时文件
     */
    public void delete() throws IOException {
    	file.delete();
    }
    
    /**
     * 未实现
     */
    public String getHeader(String name) {
    	return null;
    }

    /**
     * 未实现
     */
    public Collection<String> getHeaders(String name) {
    	return new ArrayList<String>();
    }

    /**
     * 未实现
     */
    public Collection<String> getHeaderNames() {
    	return new ArrayList<String>();
    }
}

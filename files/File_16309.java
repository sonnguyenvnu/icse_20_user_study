package org.hswebframework.web.dictionary.simple.parser;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * @author zhouhao
 */
public class DictParserFormat implements Serializable {
    //字典选项间的分割符
    private String splitter = ",";

    //�?节点间的分割符
    private String childSplitter = ",";

    //�?节点开始分割符
    private String childStartChar = "(";

    //�?节点结�?�分割符
    private String childEndChar = ")";

    //�?缀
    private String prefix = "";

    //�?�缀
    private String suffix = "";

    public String getSplitter() {
        return splitter;
    }

    public void setSplitter(String splitter) {
        this.splitter = splitter;
    }

    public String getChildSplitter() {
        return childSplitter;
    }

    public void setChildSplitter(String childSplitter) {
        this.childSplitter = childSplitter;
    }

    public String getChildStartChar() {
        return childStartChar;
    }

    public void setChildStartChar(String childStartChar) {
        this.childStartChar = childStartChar;
    }

    public String getChildEndChar() {
        return childEndChar;
    }

    public void setChildEndChar(String childEndChar) {
        this.childEndChar = childEndChar;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public StringJoiner createJoiner() {
        return new StringJoiner(splitter, prefix, suffix);
    }
}

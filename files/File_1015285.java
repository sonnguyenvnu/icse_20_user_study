package info.xiaomo.core.untils;

import com.alibaba.fastjson.JSON;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.*;
import java.net.URL;
import java.util.List;

/**
 * @author : xiaomo
 */
public class XmlUtil {
    /**
     * 解�?url xml文档
     *
     * @param url
     */
    public static Document parse(URL url) throws DocumentException {
        SAXReader reader = new SAXReader();
        return reader.read(url);
    }

    /**
     * �??历解�?文档
     */
    public static void treeWalk(Document document) {
        treeWalk(document.getRootElement());
    }

    /**
     * �??历解�?元素
     */
    public static void treeWalk(Element element) {
        for (int i = 0, size = element.nodeCount(); i < size; i++) {
            Node node = element.node(i);
            if (node instanceof Element) {
                treeWalk((Element) node);
            }

        }
    }

    /**
     * 解�?文件，获得根元素
     */
    public static Element parse(String xmlPath, String encoding) throws Exception {
        //文件是�?�存在
        File file = new File(xmlPath);
        if (!file.exists()) {
            throw new Exception("找�?到xml文件：" + xmlPath);
        }

        //解�?
        SAXReader reader = new SAXReader(false);
        Document doc = reader.read(new FileInputStream(file), encoding);
        return doc.getRootElement();
    }

    /**
     * �?存文档
     *
     * @throws Exception
     */
    public static void save(Document doc, String xmlPath, String encoding) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(encoding);
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(new FileOutputStream(xmlPath), encoding), format);
        writer.write(doc);
        writer.flush();
        writer.close();
    }

    /**
     * 修改xml�?节点的值
     *
     * @param inputXml      原xml文件
     * @param nodes         �?修改的节点
     * @param attributeName 属性�??称
     * @param value         新值
     * @param outXml        输出文件路径�?�文件�?? 如果输出文件为null，则默认为原xml文件
     */
    public static void modifyDocument(File inputXml, String nodes, String attributeName, String value, String outXml) {
        try {
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputXml);
            List list = document.selectNodes(nodes);
            for (Object aList : list) {
                Attribute attribute = (Attribute) aList;
                if (attribute.getName().equals(attributeName)) {
                    attribute.setValue(value);
                }
            }
            XMLWriter output;
            //指定输出文件
            if (outXml != null) {
                output = new XMLWriter(new FileWriter(new File(outXml)));
            } else { //输出文件为原文件
                output = new XMLWriter(new FileWriter(inputXml));
            }
            output.write(document);
            output.close();
        } catch (DocumentException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * xml转�?�为字符串
     *
     * @param doc
     * @param encoding
     * @return
     * @throws Exception
     */
    public static String toString(Document doc, String encoding) throws Exception {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(encoding);
        ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
        XMLWriter writer = new XMLWriter(new OutputStreamWriter(byteOS, encoding), format);
        writer.write(doc);
        writer.flush();
        writer.close();
        return byteOS.toString(encoding);
    }

    /**
     * 字符串转�?�为Document
     *
     * @param text
     * @return
     * @throws DocumentException
     */
    public static Document str2Document(String text) throws DocumentException {
        return DocumentHelper.parseText(text);
    }

    public static void main(String[] args) throws Exception {
        Element parse = parse("E:\\thinkpage_cities.xls", "UTF8");
        System.out.println(JSON.toJSONString(parse));
    }
}

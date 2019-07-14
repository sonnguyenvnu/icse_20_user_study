package com.roncoo.pay.reconciliation.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {
    /**
     * 将xml string 转化为map
     *
     * @param xmlDoc
     * @return
     * @throws IOException
     * @throws
     */
    @SuppressWarnings("unchecked")
	/*public static Map<String, Object> xmlToMap(String xmlDoc) throws JDOMException, IOException {
		// 创建一个新的字符串
		StringReader read = new StringReader(xmlDoc);
		// 创建新的输入�?SAX 解�?器将使用 InputSource 对象�?�确定如何读�?� XML 输入
		InputSource source = new InputSource(read);
		// 创建一个新的SAXBuilder
		SAXBuilder sb = new SAXBuilder();

		Map<String, Object> xmlMap = new HashMap<String, Object>();

		Document doc = sb.build(source); // 通过输入�?构造一个Document
		Element root = doc.getRootElement(); // �?�的根元素

		List<Element> cNodes = root.getChildren(); // 得到根元素所有�?元素的集�?�(根元素的�?节点，�?包括孙�?节点)
		Element et = null;
		for (int i = 0; i < cNodes.size(); i++) {
			et = (Element) cNodes.get(i); // 循环�?次得到�?元素
			xmlMap.put(et.getName(), et.getText());
		}
		return xmlMap;
	}*/

    public static Map<String, Object> xmlToMap(String xmlDoc) throws DocumentException {
        // 创建一个新的字符串
        StringReader read = new StringReader(xmlDoc);
        // 创建新的输入�?SAX 解�?器将使用 InputSource 对象�?�确定如何读�?� XML 输入
        InputSource source = new InputSource(read);
        // 创建一个新的SAXBuilder
        SAXReader saxReader = new SAXReader();

        Map<String, Object> xmlMap = new HashMap<String, Object>();

        Document doc = saxReader.read(source); // 通过输入�?构造一个Document
        Element root = doc.getRootElement(); // �?�的根元素

        List<Element> elements = root.elements(); // 得到根元素所有�?元素的集�?�(根元素的�?节点，�?包括孙�?节点)
        for (Element et : elements) {
            xmlMap.put(et.getName(), et.getText());
        }
        return xmlMap;
    }


}

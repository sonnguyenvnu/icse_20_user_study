/** 
 * ??????????
 */
public static Element parse(String xmlPath,String encoding) throws Exception {
  File file=new File(xmlPath);
  if (!file.exists()) {
    throw new Exception("???xml???" + xmlPath);
  }
  SAXReader reader=new SAXReader(false);
  Document doc=reader.read(new FileInputStream(file),encoding);
  return doc.getRootElement();
}

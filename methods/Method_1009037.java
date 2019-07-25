/** 
 * @param source
 * @param result
 * @param omit_xml_declaration
 * @param method_xml
 * @param indent
 * @throws Docx4JException
 * @since 3.3.1
 */
public static void serialize(Source source,Result result,boolean omit_xml_declaration,boolean method_xml,boolean indent) throws Docx4JException {
  try {
    Transformer serializer=new org.docx4j.org.apache.xalan.transformer.TransformerIdentityImpl();
    if (omit_xml_declaration) {
      serializer.setOutputProperty(javax.xml.transform.OutputKeys.OMIT_XML_DECLARATION,"yes");
    }
    if (method_xml) {
      serializer.setOutputProperty(javax.xml.transform.OutputKeys.METHOD,"xml");
    }
    if (indent) {
      serializer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT,"yes");
    }
    serializer.transform(source,result);
  }
 catch (  Exception e) {
    log.error(e.getMessage(),e);
    throw new Docx4JException("Exception writing Document to OutputStream: " + e.getMessage(),e);
  }
}

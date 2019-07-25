/** 
 * Generate XSL FO for the entire MainDocumentPart.
 * @return
 */
public org.w3c.dom.Document export() throws Docx4JException {
  ByteArrayOutputStream outStream=new ByteArrayOutputStream(DEFAULT_OUTPUT_SIZE);
  Document ret=null;
  foSettings.setApacheFopMime(FOSettings.INTERNAL_FO_MIME);
  try {
    Docx4J.toFO(foSettings,outStream,Docx4J.FLAG_EXPORT_PREFER_NONXSL);
    ret=XmlUtils.getNewDocumentBuilder().parse(new ByteArrayInputStream(outStream.toByteArray()));
  }
 catch (  Docx4JException e) {
    log.error("Exception exporting document: " + e.getMessage(),e);
  }
catch (  SAXException e) {
    log.error("Exception parsing document: " + e.getMessage(),e);
  }
catch (  IOException e) {
    log.error("Exception parsing document: " + e.getMessage(),e);
  }
  return ret;
}

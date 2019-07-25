/** 
 * Create a pdf version of the document. 
 * @param os The OutputStream to write the pdf to 
 */
public void output(OutputStream os) throws Docx4JException {
  try {
    org.w3c.dom.Document xhtmlDoc=org.docx4j.XmlUtils.neww3cDomDocument();
    javax.xml.transform.dom.DOMResult result=new javax.xml.transform.dom.DOMResult(xhtmlDoc);
    AbstractHtmlExporter exporter=new HtmlExporter();
    exporter.html(wordMLPackage,result,false,System.getProperty("java.io.tmpdir"));
    org.xhtmlrenderer.pdf.ITextRenderer renderer=new org.xhtmlrenderer.pdf.ITextRenderer();
    org.xhtmlrenderer.extend.FontResolver resolver=renderer.getFontResolver();
    Map fontsInUse=wordMLPackage.getMainDocumentPart().fontsInUse();
    Iterator fontMappingsIterator=fontsInUse.entrySet().iterator();
    while (fontMappingsIterator.hasNext()) {
      Map.Entry pairs=(Map.Entry)fontMappingsIterator.next();
      if (pairs.getKey() == null) {
        log.info("Skipped null key");
        pairs=(Map.Entry)fontMappingsIterator.next();
      }
      String fontName=(String)pairs.getKey();
      PhysicalFont pf=wordMLPackage.getFontMapper().getFontMappings().get(fontName);
      if (pf == null) {
        log.error("Document font " + fontName + " is not mapped to a physical font!");
        continue;
      }
      embed(renderer,pf);
      PhysicalFont pfVariation=PhysicalFonts.getBoldForm(pf);
      if (pfVariation != null) {
        embed(renderer,pfVariation);
      }
      pfVariation=PhysicalFonts.getBoldItalicForm(pf);
      if (pfVariation != null) {
        embed(renderer,pfVariation);
      }
      pfVariation=PhysicalFonts.getItalicForm(pf);
      if (pfVariation != null) {
        embed(renderer,pfVariation);
      }
    }
    renderer.setDocument(xhtmlDoc,null);
    renderer.layout();
    renderer.createPDF(os);
  }
 catch (  Exception e) {
    throw new Docx4JException("Failed creating PDF via HTML ",e);
  }
}

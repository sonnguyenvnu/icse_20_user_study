/** 
 * Create a pdf version of the document, using XSL FO. 
 * @param os The OutputStream to write the pdf to 
 */
@Override public void output(OutputStream os,PdfSettings settings) throws Docx4JException {
  pdfDoc=new Document(PageSize.A4,50,50,70,70);
  try {
    PdfWriter writer=PdfWriter.getInstance(pdfDoc,os);
    writer.setPageEvent(new EndPage());
    pdfDoc.open();
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
      BaseFont bf=BaseFont.createFont(pf.getEmbeddedFile(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
      baseFonts.put(fontName,bf);
      PhysicalFont pfVariation=PhysicalFonts.getBoldForm(pf);
      if (pfVariation != null) {
        bf=BaseFont.createFont(pfVariation.getEmbeddedFile(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        baseFonts.put(fontName + BOLD,bf);
      }
      pfVariation=PhysicalFonts.getBoldItalicForm(pf);
      if (pfVariation != null) {
        bf=BaseFont.createFont(pfVariation.getEmbeddedFile(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        baseFonts.put(fontName + BOLD_ITALIC,bf);
      }
      pfVariation=PhysicalFonts.getItalicForm(pf);
      if (pfVariation != null) {
        bf=BaseFont.createFont(pfVariation.getEmbeddedFile(),BaseFont.IDENTITY_H,BaseFont.NOT_EMBEDDED);
        baseFonts.put(fontName + ITALIC,bf);
      }
    }
    org.docx4j.wml.Document wmlDocumentEl=wordMLPackage.getMainDocumentPart().getJaxbElement();
    Body body=wmlDocumentEl.getBody();
    List<Object> bodyChildren=body.getEGBlockLevelElts();
    traverseBlockLevelContent(bodyChildren,pdfDoc);
  }
 catch (  Exception e) {
    throw new Docx4JException("iTextissues",e);
  }
 finally {
    try {
      pdfDoc.close();
      os.close();
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
}

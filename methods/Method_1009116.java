/** 
 * Create a pdf version of the document, using XSL FO. 
 * @param os The OutputStream to write the pdf to 
 * @param settings The configuration for the conversion 
 */
public void output(OutputStream os,PdfSettings settings) throws Docx4JException {
  setupSettings(settings,FOSettings.INTERNAL_FO_MIME);
  Docx4J.toPDF(wordMLPackage,os);
}

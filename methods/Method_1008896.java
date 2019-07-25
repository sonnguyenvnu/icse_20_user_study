/** 
 * @param wmlPackage
 * @throws Exception
 * @since 8.1.0
 */
public static void prepare(WordprocessingMLPackage wmlPackage,int flags) throws Exception {
  WordprocessingMLPackage.FilterSettings filterSettings=new WordprocessingMLPackage.FilterSettings();
  if (flags == FLAG_NONE) {
    flags=(FLAG_REMOVE_PROOF_ERRORS | FLAG_REMOVE_CONTENT_CONTROLS | FLAG_REMOVE_RSIDS | FLAG_REMOVE_BOOKMARKS);
  }
  if ((flags & FLAG_REMOVE_PROOF_ERRORS) == FLAG_REMOVE_PROOF_ERRORS) {
    filterSettings.setRemoveProofErrors(true);
  }
  if ((flags & FLAG_REMOVE_CONTENT_CONTROLS) == FLAG_REMOVE_CONTENT_CONTROLS) {
    filterSettings.setRemoveContentControls(true);
  }
  if ((flags & FLAG_REMOVE_RSIDS) == FLAG_REMOVE_RSIDS) {
    filterSettings.setRemoveRsids(true);
  }
  if ((flags & FLAG_REMOVE_BOOKMARKS) == FLAG_REMOVE_BOOKMARKS) {
    filterSettings.setRemoveBookmarks(true);
  }
  wmlPackage.filter(filterSettings);
  if (log.isInfoEnabled()) {
    log.info(XmlUtils.marshaltoString(wmlPackage.getMainDocumentPart().getJaxbElement(),true,true));
  }
  org.docx4j.wml.Document wmlDocumentEl=wmlPackage.getMainDocumentPart().getJaxbElement();
  Body body=wmlDocumentEl.getBody();
  SingleTraversalUtilVisitorCallback paragraphVisitor=new SingleTraversalUtilVisitorCallback(new TraversalUtilParagraphVisitor());
  paragraphVisitor.walkJAXBElements(body);
  if (log.isInfoEnabled()) {
    log.info(XmlUtils.marshaltoString(wmlPackage.getMainDocumentPart().getJaxbElement(),true,true));
  }
}

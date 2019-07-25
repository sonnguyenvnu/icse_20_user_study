/** 
 * ????doc
 * @param doc
 */
public void reload(NiceXWPFDocument doc){
  try {
    this.close();
  }
 catch (  IOException e) {
    logger.error("Close failed",e);
  }
  this.doc=doc;
  this.eleTemplates=this.visitor.visitDocument(doc);
}

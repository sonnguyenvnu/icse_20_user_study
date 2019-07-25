/** 
 * Reports a change in XML.
 * @param e   The diff-x event that has been inserted or deleted.
 * @param mod The modification flag (positive for inserts, negative for deletes).
 * @throws IOException an I/O exception if an error occurs.
 */
private void change(DiffXEvent e,int mod) throws IOException {
  if (!this.isSetup) {
    setUpXML();
  }
  if (e instanceof OpenElementEvent) {
    flushAttributes();
    endTextChange();
    this.xml.openElement(mod > 0 ? Constants.INSERT_NS_URI : Constants.DELETE_NS_URI,"element",false);
    this.xml.attribute("name",((OpenElementEvent)e).getName());
    this.xml.attribute("ns-uri",((OpenElementEvent)e).getURI());
  }
 else   if (e instanceof CloseElementEvent) {
    flushAttributes();
    endTextChange();
    this.xml.closeElement();
  }
 else   if (e instanceof TextEvent) {
    flushAttributes();
    switchTextChange(mod);
    e.toXML(this.xml);
    if (this.config.isIgnoreWhiteSpace() && !this.config.isPreserveWhiteSpace()) {
      this.xml.writeXML(" ");
    }
  }
 else   if (e instanceof AttributeEvent) {
    if (mod > 0) {
      this.insAttributes.push((AttributeEvent)e);
    }
 else {
      this.delAttributes.push((AttributeEvent)e);
    }
  }
 else   if (e instanceof ProcessingInstructionEvent) {
    flushAttributes();
    endTextChange();
    this.xml.openElement(mod > 0 ? Constants.INSERT_NS_URI : Constants.DELETE_NS_URI,"processing-instruction",false);
    this.xml.attribute("data",((ProcessingInstructionEvent)e).getData());
    this.xml.attribute("target",((ProcessingInstructionEvent)e).getTarget());
  }
 else {
    flushAttributes();
    endTextChange();
    e.toXML(this.xml);
  }
  this.xml.flush();
}

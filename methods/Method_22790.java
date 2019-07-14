/** 
 * Set document with a twist, includes the old caret and scroll positions, added for p5. [fry]
 */
public void setDocument(SyntaxDocument document,int start,int stop,int scroll){
  if (this.document == document)   return;
  if (this.document != null)   this.document.removeDocumentListener(documentHandler);
  this.document=document;
  document.addDocumentListener(documentHandler);
  bracketHelper.invalidate();
  select(start,stop);
  updateScrollBars();
  setVerticalScrollPosition(scroll);
  painter.repaint();
}

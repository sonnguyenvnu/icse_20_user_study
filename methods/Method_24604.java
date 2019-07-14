/** 
 * Attach a  {@link Document} to enable line number tracking when editing.The position to track is before the first non-whitespace character on the line. Edits happening before that position will cause the line number to update accordingly. Multiple  {@link #startTracking} calls will replacethe tracked document. Whoever wants a tracked line should track it and add itself as listener if necessary. ( {@link LineHighlight},  {@link LineBreakpoint})
 * @param doc the {@link Document} to use for line number tracking
 */
public synchronized void startTracking(Document doc){
  if (doc == null) {
    return;
  }
  if (doc == this.doc) {
    return;
  }
  try {
    Element line=doc.getDefaultRootElement().getElement(lineIdx);
    if (line == null) {
      return;
    }
    String lineText=doc.getText(line.getStartOffset(),line.getEndOffset() - line.getStartOffset());
    pos=doc.createPosition(line.getStartOffset() + nonWhiteSpaceOffset(lineText));
    this.doc=doc;
    doc.addDocumentListener(this);
  }
 catch (  BadLocationException ex) {
    Messages.loge(null,ex);
    pos=null;
    this.doc=null;
  }
}

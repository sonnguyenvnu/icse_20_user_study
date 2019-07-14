private void checkLength() throws BadLocationException {
  int docLength=getLength();
  if (docLength > maxCharCount) {
    remove(0,docLength - maxCharCount);
  }
  Element element=super.getDefaultRootElement();
  int lineCount=element.getElementCount();
  int overage=lineCount - maxLineCount;
  if (overage > 0) {
    Element lineElement=element.getElement(overage);
    if (lineElement != null) {
      int endOffset=lineElement.getEndOffset();
      super.remove(0,endOffset);
    }
  }
}

protected void documentChanged(DocumentEvent evt){
  bracketHelper.invalidate();
  DocumentEvent.ElementChange ch=evt.getChange(document.getDefaultRootElement());
  int count;
  if (ch == null)   count=0;
 else   count=ch.getChildrenAdded().length - ch.getChildrenRemoved().length;
  int line=getLineOfOffset(evt.getOffset());
  if (count == 0) {
    painter.invalidateLine(line);
  }
 else   if (line < firstLine) {
    setFirstLine(line);
  }
 else {
    painter.invalidateLineRange(line,firstLine + visibleLines);
    updateScrollBars();
  }
}

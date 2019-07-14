protected void paintLineHighlight(Graphics gfx,int line,int y){
  int height=fm.getHeight();
  y+=fm.getLeading() + fm.getMaxDescent();
  int selectionStart=textArea.getSelectionStart();
  int selectionEnd=textArea.getSelectionStop();
  if (selectionStart == selectionEnd) {
    if (defaults.lineHighlight) {
      gfx.setColor(defaults.lineHighlightColor);
      gfx.fillRect(0,y,getWidth(),height);
    }
  }
 else {
    gfx.setColor(defaults.selectionColor);
    int selectionStartLine=textArea.getSelectionStartLine();
    int selectionEndLine=textArea.getSelectionStopLine();
    int lineStart=textArea.getLineStartOffset(line);
    int x1, x2;
    if (selectionStartLine == selectionEndLine) {
      x1=textArea._offsetToX(line,selectionStart - lineStart);
      x2=textArea._offsetToX(line,selectionEnd - lineStart);
    }
 else     if (line == selectionStartLine) {
      x1=textArea._offsetToX(line,selectionStart - lineStart);
      x2=getWidth();
    }
 else     if (line == selectionEndLine) {
      x1=textArea._offsetToX(line,0);
      x2=textArea._offsetToX(line,selectionEnd - lineStart);
    }
 else {
      x1=textArea._offsetToX(line,0);
      x2=getWidth();
    }
    gfx.fillRect(x1 > x2 ? x2 : x1,y,x1 > x2 ? (x1 - x2) : (x2 - x1),height);
  }
}

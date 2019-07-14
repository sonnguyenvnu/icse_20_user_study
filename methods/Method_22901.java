protected void handleCommentUncomment(){
  startCompoundEdit();
  String prefix=getCommentPrefix();
  int prefixLen=prefix.length();
  int startLine=textarea.getSelectionStartLine();
  int stopLine=textarea.getSelectionStopLine();
  int lastLineStart=textarea.getLineStartOffset(stopLine);
  int selectionStop=textarea.getSelectionStop();
  if (selectionStop == lastLineStart) {
    if (textarea.isSelectionActive()) {
      stopLine--;
    }
  }
  boolean commented=true;
  for (int i=startLine; commented && (i <= stopLine); i++) {
    String lineText=textarea.getLineText(i).trim();
    if (lineText.length() == 0) {
      continue;
    }
    commented=lineText.startsWith(prefix);
  }
  int lso=Math.abs(textarea.getLineStartNonWhiteSpaceOffset(startLine) - textarea.getLineStartOffset(startLine));
  if (!commented) {
    for (int line=startLine + 1; line <= stopLine; line++) {
      String lineText=textarea.getLineText(line);
      if (lineText.trim().length() == 0) {
        continue;
      }
      int so=Math.abs(textarea.getLineStartNonWhiteSpaceOffset(line) - textarea.getLineStartOffset(line));
      lso=Math.min(lso,so);
    }
  }
  for (int line=startLine; line <= stopLine; line++) {
    int location=textarea.getLineStartNonWhiteSpaceOffset(line);
    String lineText=textarea.getLineText(line);
    if (lineText.trim().length() == 0)     continue;
    if (commented) {
      textarea.select(location,location + prefixLen);
      textarea.setSelectedText("");
    }
 else {
      location=textarea.getLineStartOffset(line) + lso;
      textarea.select(location,location);
      textarea.setSelectedText(prefix);
    }
  }
  textarea.select(textarea.getLineStartOffset(startLine),textarea.getLineStopOffset(stopLine) - 1);
  stopCompoundEdit();
  sketch.setModified(true);
}

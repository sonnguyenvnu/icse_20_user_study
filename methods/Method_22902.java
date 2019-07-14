public void handleIndentOutdent(boolean indent){
  int tabSize=Preferences.getInteger("editor.tabs.size");
  String tabString=Editor.EMPTY.substring(0,tabSize);
  startCompoundEdit();
  int startLine=textarea.getSelectionStartLine();
  int stopLine=textarea.getSelectionStopLine();
  int lastLineStart=textarea.getLineStartOffset(stopLine);
  int selectionStop=textarea.getSelectionStop();
  if (selectionStop == lastLineStart) {
    if (textarea.isSelectionActive()) {
      stopLine--;
    }
  }
  for (int line=startLine; line <= stopLine; line++) {
    int location=textarea.getLineStartOffset(line);
    if (indent) {
      textarea.select(location,location);
      textarea.setSelectedText(tabString);
    }
 else {
      int last=Math.min(location + tabSize,textarea.getDocumentLength());
      textarea.select(location,last);
      if (tabString.equals(textarea.getSelectedText())) {
        textarea.setSelectedText("");
      }
    }
  }
  textarea.select(textarea.getLineStartOffset(startLine),textarea.getLineStopOffset(stopLine) - 1);
  stopCompoundEdit();
  sketch.setModified(true);
}

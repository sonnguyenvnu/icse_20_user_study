/** 
 * Event handler called when switching between tabs. Loads all line background colors set for the tab.
 * @param code tab to switch to
 */
@Override public void setCode(SketchCode code){
  Document oldDoc=code.getDocument();
  super.setCode(code);
  Document newDoc=code.getDocument();
  if (oldDoc != newDoc && pdex != null) {
    pdex.documentChanged(newDoc);
  }
  final JavaTextArea ta=getJavaTextArea();
  if (ta != null) {
    ta.clearGutterText();
    if (breakpointedLines != null) {
      for (      LineHighlight hl : breakpointedLines) {
        if (isInCurrentTab(hl.getLineID())) {
          hl.paint();
        }
      }
    }
    if (currentLine != null) {
      if (isInCurrentTab(currentLine.getLineID())) {
        currentLine.paint();
      }
    }
  }
  if (getDebugger() != null && getDebugger().isStarted()) {
    getDebugger().startTrackingLineChanges();
  }
  if (errorColumn != null) {
    errorColumn.repaint();
  }
}

/** 
 * Calculates location of caret and displays the suggestion pop-up.
 */
protected void showSuggestion(DefaultListModel<CompletionCandidate> listModel,String subWord){
  hideSuggestion();
  if (listModel.size() != 0) {
    int position=getCaretPosition();
    try {
      Point location=new Point(offsetToX(getCaretLine(),position - getLineStartOffset(getCaretLine())),lineToY(getCaretLine()) + getPainter().getLineHeight());
      suggestion=new CompletionPanel(this,position,subWord,listModel,location,getJavaEditor());
      requestFocusInWindow();
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
 else {
    Messages.log("TextArea: No suggestions to show.");
  }
}

/** 
 * Handles events from InputMethod.
 * @param event event from Input Method.
 */
@Override public void inputMethodTextChanged(InputMethodEvent event){
  if (Base.DEBUG) {
    StringBuilder sb=new StringBuilder();
    sb.append("#Called inputMethodTextChanged");
    sb.append("\t ID: " + event.getID());
    sb.append("\t timestamp: " + new java.util.Date(event.getWhen()));
    sb.append("\t parmString: " + event.paramString());
    Messages.log(sb.toString());
  }
  AttributedCharacterIterator text=event.getText();
  committedCount=event.getCommittedCharacterCount();
  textArea.setCaretVisible(false);
  if (text != null && text.getEndIndex() - (text.getBeginIndex() + committedCount) <= 0) {
    textArea.setCaretVisible(true);
  }
  if (text == null) {
    textArea.setCaretVisible(true);
  }
  if (text != null) {
    if (committedCount > 0) {
      char[] insertion=new char[committedCount];
      char c=text.first();
      for (int i=0; i < committedCount; i++) {
        insertion[i]=c;
        c=text.next();
      }
      textArea.setSelectedText(new String(insertion),true);
      textArea.getInputHandler().handleInputMethodCommit();
    }
    CompositionTextPainter compositionPainter=textArea.getPainter().getCompositionTextpainter();
    Messages.log("textArea.getCaretPosition() + committed_count: " + (textArea.getCaretPosition() + committedCount));
    compositionPainter.setComposedTextLayout(getTextLayout(text,committedCount),textArea.getCaretPosition() + committedCount);
    compositionPainter.setCaret(event.getCaret());
  }
 else {
    CompositionTextPainter compositionPainter=textArea.getPainter().getCompositionTextpainter();
    compositionPainter.setComposedTextLayout(null,0);
    compositionPainter.setCaret(null);
  }
  event.consume();
  textArea.repaint();
}

/** 
 * Called when a user fixed text from input method or delete all composition text. This method resets CompositionTextPainter.
 * @param text Text from InputMethodEvent.
 * @param committed_count Numbers of committed characters in text.
 */
public void endCompositionText(AttributedCharacterIterator text,int committed_count){
  if (committed_count == 0) {
    removeNotCommittedText(text);
  }
  CompositionTextPainter compositionPainter=textArea.getPainter().getCompositionTextpainter();
  compositionPainter.invalidateComposedTextLayout(initialCaretPosition + committed_count);
  prevComposeString="";
  isInputProcess=false;
}

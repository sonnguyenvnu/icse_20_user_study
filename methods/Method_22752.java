/** 
 * Called when a user begins input from input method. This method initializes text manager.
 * @param text Text from InputMethodEvent.
 * @param committed_count Numbers of committed characters in text.
 */
public void beginCompositionText(AttributedCharacterIterator text,int committed_count){
  isInputProcess=true;
  prevComposeString="";
  initialCaretPosition=textArea.getCaretPosition();
  processCompositionText(text,committed_count);
}

/** 
 * Called when a user processing input characters and select candidates from input method.
 * @param text Text from InputMethodEvent.
 * @param committed_count Numbers of committed characters in text.
 */
public void processCompositionText(AttributedCharacterIterator text,int committed_count){
  int layoutCaretPosition=initialCaretPosition + committed_count;
  CompositionTextPainter compositionPainter=textArea.getPainter().getCompositionTextpainter();
  compositionPainter.setComposedTextLayout(getTextLayout(text,committed_count),layoutCaretPosition);
  int textLength=text.getEndIndex() - text.getBeginIndex() - committed_count;
  StringBuilder unCommitedStringBuf=new StringBuilder(textLength);
  char c;
  for (c=text.setIndex(committed_count); c != CharacterIterator.DONE && textLength > 0; c=text.next(), --textLength) {
    unCommitedStringBuf.append(c);
  }
  String unCommittedString=unCommitedStringBuf.toString();
  try {
    if (canRemovePreviousInput(committed_count)) {
      textArea.getDocument().remove(layoutCaretPosition,prevComposeString.length());
    }
    textArea.getDocument().insertString(layoutCaretPosition,unCommittedString,null);
    if (committed_count > 0) {
      initialCaretPosition=initialCaretPosition + committed_count;
    }
    prevComposeString=unCommittedString;
    prevCommittedCount=committed_count;
  }
 catch (  BadLocationException e) {
    e.printStackTrace();
  }
}

/** 
 * Insert full width space
 */
public void insertFullWidthSpace(){
  initialCaretPosition=textArea.getCaretPosition();
  int layoutCaretPosition=initialCaretPosition;
  try {
    textArea.getDocument().insertString(layoutCaretPosition,"\u3000",null);
  }
 catch (  BadLocationException e) {
    e.printStackTrace();
  }
}

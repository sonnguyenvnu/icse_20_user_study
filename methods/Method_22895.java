/** 
 * Get the position (character offset) of the caret. With text selected, this will be the last character actually selected, no matter the direction of the selection. That is, if the user clicks and drags to select lines 7 up to 4, then the caret position will be somewhere on line four.
 */
public int getCaretOffset(){
  return textarea.getCaretPosition();
}

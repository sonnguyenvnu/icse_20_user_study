/** 
 * This method should be called by caret blinking thread (timer) to show/hide the caret.
 */
public void tick(){
  if (System.currentTimeMillis() - myChangeCaretTimeStamp < 500) {
    return;
  }
  myCaretIsVisible=!myCaretIsVisible;
}

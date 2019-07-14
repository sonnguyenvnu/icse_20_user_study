/** 
 * Ensures that the caret is visible by scrolling the text area if necessary.
 * @return True if scrolling was actually performed, false if thecaret was already visible
 */
public boolean scrollToCaret(){
  int line=getCaretLine();
  int lineStart=getLineStartOffset(line);
  int offset=Math.max(0,Math.min(getLineLength(line) - 1,getCaretPosition() - lineStart));
  return scrollTo(line,offset);
}

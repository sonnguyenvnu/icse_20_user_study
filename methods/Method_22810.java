/** 
 * Similar to <code>setSelectedText()</code>, but overstrikes the appropriate number of characters if overwrite mode is enabled.
 * @param str The string
 * @see #setSelectedText(String)
 * @see #isOverwriteEnabled()
 */
public void overwriteSetSelectedText(String str){
  if (!overwrite || selectionStart != selectionEnd) {
    boolean isSelectAndReplaceOp=(selectionStart != selectionEnd);
    setSelectedText(str,isSelectAndReplaceOp);
    return;
  }
  int caret=getCaretPosition();
  int caretLineEnd=getLineStopOffset(getCaretLine());
  if (caretLineEnd - caret <= str.length()) {
    setSelectedText(str,false);
    return;
  }
  try {
    document.remove(caret,str.length());
    document.insertString(caret,str,null);
  }
 catch (  BadLocationException bl) {
    bl.printStackTrace();
  }
}

/** 
 * Selects the text in the text area.
 */
@Override public HyperlinkEvent execute(){
  textArea.select(selStart,selEnd);
  return null;
}

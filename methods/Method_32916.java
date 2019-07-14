/** 
 * If DECSET 2004 is set, prefix paste with "\033[200~" and suffix with "\033[201~". 
 */
public void paste(String text){
  text=text.replaceAll("(\u001B|[\u0080-\u009F])","");
  text=text.replaceAll("\r?\n","\r");
  boolean bracketed=isDecsetInternalBitSet(DECSET_BIT_BRACKETED_PASTE_MODE);
  if (bracketed)   mSession.write("\033[200~");
  mSession.write(text);
  if (bracketed)   mSession.write("\033[201~");
}

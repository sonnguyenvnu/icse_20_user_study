/** 
 * Emits characters into the local text buffer.
 */
protected void textEmitChar(final char c){
  ensureCapacity();
  text[textLen++]=c;
}

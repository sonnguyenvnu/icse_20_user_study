/** 
 * Called from textFont. Check the validity of args and print possible errors to the user before calling this. Subclasses will want to override this one.
 * @param which font to set, not null
 * @param size size to set, greater than zero
 */
protected void textFontImpl(PFont which,float size){
  textFont=which;
  handleTextSize(size);
}

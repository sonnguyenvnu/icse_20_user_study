/** 
 * @param size the size of the letters in units of pixels
 */
public void textFont(PFont which,float size){
  if (which == null) {
    throw new RuntimeException(ERROR_TEXTFONT_NULL_PFONT);
  }
  if (size <= 0) {
    System.err.println("textFont: ignoring size " + size + " px:" + "the text size must be larger than zero");
    size=textSize;
  }
  textFontImpl(which,size);
}

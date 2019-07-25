/** 
 * Sets the current document and resets the current position to the start of it.
 * @param text
 */
public void reset(String text){
  if (text == null) {
    throw new NullPointerException("\"text\" should not be null");
  }
  text_=text;
  position_=0;
}

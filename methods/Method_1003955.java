/** 
 * Resets this  {@code TwitterTokenStream} (and also downstream tokens if they exist) to parse a newinput.
 */
public void reset(CharSequence input){
  updateInputCharSequence(input);
  reset();
}

/** 
 * Convert a character offset to a horizontal pixel position inside the text area. Overridden to take gutter width into account.
 * @param line the 0-based line number
 * @param offset the character offset (0 is the first character on a line)
 * @return the horizontal position
 */
@Override public int _offsetToX(int line,int offset){
  return super._offsetToX(line,offset) + Editor.LEFT_GUTTER;
}

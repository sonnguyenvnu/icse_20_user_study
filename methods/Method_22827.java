/** 
 * Convert a horizontal pixel position to a character offset. Overridden to take gutter width into account.
 * @param line the 0-based line number
 * @param x the horizontal pixel position
 * @return he character offset (0 is the first character on a line)
 */
@Override public int xToOffset(int line,int x){
  return super.xToOffset(line,x - Editor.LEFT_GUTTER);
}

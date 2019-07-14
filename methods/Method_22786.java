/** 
 * Converts an offset in a line into an x co-ordinate. This is a slow version that can be used any time.
 * @param line The line
 * @param offset The offset, from the start of the line
 */
public final int offsetToX(int line,int offset){
  painter.currentLineTokens=null;
  return _offsetToX(line,offset);
}

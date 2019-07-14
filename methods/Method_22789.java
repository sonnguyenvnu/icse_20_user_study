/** 
 * Converts a point to an offset, from the start of the text.
 * @param x The x co-ordinate of the point
 * @param y The y co-ordinate of the point
 */
public int xyToOffset(int x,int y){
  int line=yToLine(y);
  int start=getLineStartOffset(line);
  return start + xToOffset(line,x);
}

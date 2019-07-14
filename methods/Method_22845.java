/** 
 * Returns next tab stop after a specified point. 
 */
@Override public float nextTabStop(float x,int tabOffset){
  int offset=textArea.getHorizontalOffset();
  int ntabs=((int)x - offset) / tabSize;
  return (ntabs + 1) * tabSize + offset;
}

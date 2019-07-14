/** 
 * Try parse a color from a text parameter and into a specified index. 
 */
public void tryParseColor(int intoIndex,String textParameter){
  int c=parse(textParameter);
  if (c != 0)   mCurrentColors[intoIndex]=c;
}

/** 
 * @return a default ordering for numbers (including periods).
 */
public static char[] getDefaultNumberList(){
  final char[] charList=new char[11];
  charList[0]=EMPTY_CHAR;
  for (int i=0; i < 10; i++) {
    charList[i + 1]=(char)(i + 48);
  }
  return charList;
}

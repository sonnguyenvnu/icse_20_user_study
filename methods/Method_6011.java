/** 
 * Returns the integer equal to the big-endian concatenation of the characters in  {@code string}as bytes. The string must be no more than four characters long.
 * @param string A string no more than four characters long.
 */
public static int getIntegerCodeForString(String string){
  int length=string.length();
  Assertions.checkArgument(length <= 4);
  int result=0;
  for (int i=0; i < length; i++) {
    result<<=8;
    result|=string.charAt(i);
  }
  return result;
}

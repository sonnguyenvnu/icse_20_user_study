/** 
 * Removes a single character from string.
 * @param string    source string
 * @param ch  character to remove
 */
public static String remove(final String string,final char ch){
  int stringLen=string.length();
  char[] result=new char[stringLen];
  int offset=0;
  for (int i=0; i < stringLen; i++) {
    char c=string.charAt(i);
    if (c == ch) {
      continue;
    }
    result[offset]=c;
    offset++;
  }
  if (offset == stringLen) {
    return string;
  }
  return new String(result,0,offset);
}

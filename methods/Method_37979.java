/** 
 * Makes a title-cased string from given input.
 */
public static String title(final String string){
  char[] chars=string.toCharArray();
  boolean wasWhitespace=true;
  for (int i=0; i < chars.length; i++) {
    char c=chars[i];
    if (CharUtil.isWhitespace(c)) {
      wasWhitespace=true;
    }
 else {
      if (wasWhitespace) {
        chars[i]=Character.toUpperCase(c);
      }
 else {
        chars[i]=Character.toLowerCase(c);
      }
      wasWhitespace=false;
    }
  }
  return new String(chars);
}

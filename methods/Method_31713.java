/** 
 * Replaces all sequences of whitespace by a single blank. Ex.: "&nbsp;&nbsp;&nbsp;&nbsp;" -> " "
 * @param str The string to analyse.
 * @return The input string, with all whitespace collapsed.
 */
public static String collapseWhitespace(String str){
  StringBuilder result=new StringBuilder();
  char previous=0;
  for (int i=0; i < str.length(); i++) {
    char c=str.charAt(i);
    if (isCharAnyOf(c,WHITESPACE_CHARS)) {
      if (previous != ' ') {
        result.append(' ');
      }
      previous=' ';
    }
 else {
      result.append(c);
      previous=c;
    }
  }
  return result.toString();
}

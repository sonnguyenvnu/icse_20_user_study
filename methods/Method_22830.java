/** 
 * Remove all trailing whitespace from a line
 */
static private String trimRight(String str){
  int i=str.length() - 1;
  while (i >= 0 && Character.isWhitespace(str.charAt(i))) {
    i--;
  }
  return str.substring(0,i + 1);
}

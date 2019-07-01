/** 
 * Remove the trailing whitespace from the specified String.
 * @param s The String to remove the trailing padding from.
 * @return The String of without the trailing padding
 */
protected String _XXXXX_(final String s){
  if (s == null || s.length() == 0) {
    return s;
  }
  int pos=s.length();
  while (pos > 0 && Character.isWhitespace(s.charAt(pos - 1))) {
    --pos;
  }
  return s.substring(0,pos);
}
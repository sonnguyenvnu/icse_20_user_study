/** 
 * @return last non-wsp in result+buf, or 0 on error.
 */
private char prevNonWhitespace(){
  StringBuffer tot=new StringBuffer();
  tot.append(result);
  tot.append(buf);
  for (int i=tot.length() - 1; i >= 0; i--) {
    if (!Character.isWhitespace(tot.charAt(i)))     return tot.charAt(i);
  }
  return 0;
}

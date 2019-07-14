/** 
 * Internal method for changing the first character case.
 */
private static String changeFirstCharacterCase(final boolean capitalize,final String string){
  int strLen=string.length();
  if (strLen == 0) {
    return string;
  }
  char ch=string.charAt(0);
  char modifiedCh;
  if (capitalize) {
    modifiedCh=Character.toUpperCase(ch);
  }
 else {
    modifiedCh=Character.toLowerCase(ch);
  }
  if (modifiedCh == ch) {
    return string;
  }
  char[] chars=string.toCharArray();
  chars[0]=modifiedCh;
  return new String(chars);
}

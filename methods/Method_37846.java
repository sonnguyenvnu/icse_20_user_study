/** 
 * Uppers lowercase ASCII char.
 */
public static char toUpperAscii(char c){
  if (isLowercaseAlpha(c)) {
    c-=(char)0x20;
  }
  return c;
}

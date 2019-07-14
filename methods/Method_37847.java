/** 
 * Lowers uppercase ASCII char.
 */
public static char toLowerAscii(char c){
  if (isUppercaseAlpha(c)) {
    c+=(char)0x20;
  }
  return c;
}

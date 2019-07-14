/** 
 * Returns ASCII value of a char. In case of overload, 0x3F is returned.
 */
public static int toAscii(final char c){
  if (c <= 0xFF) {
    return c;
  }
 else {
    return 0x3F;
  }
}

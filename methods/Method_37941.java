/** 
 * Fixes accent char.
 */
private char fixAccent(final char c){
  for (int i=0; i < ACCENT_CHARS.length; i+=2) {
    final char accentChar=ACCENT_CHARS[i];
    if (accentChar == c) {
      return ACCENT_CHARS[i + 1];
    }
  }
  return c;
}

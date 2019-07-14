/** 
 * Removes set of characters from string.
 * @param src    string
 * @param chars  characters to remove
 */
public static String removeChars(final String src,final char... chars){
  int i=src.length();
  StringBuilder sb=new StringBuilder(i);
  mainloop:   for (int j=0; j < i; j++) {
    char c=src.charAt(j);
    for (    char aChar : chars) {
      if (c == aChar) {
        continue mainloop;
      }
    }
    sb.append(c);
  }
  return sb.toString();
}

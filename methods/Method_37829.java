/** 
 * Finds index of the first character in given array the matches any from the given set of characters.
 * @return index of matched character or -1
 */
public static int findFirstEqual(final char[] source,final int index,final char[] match){
  for (int i=index; i < source.length; i++) {
    if (equalsOne(source[i],match)) {
      return i;
    }
  }
  return -1;
}

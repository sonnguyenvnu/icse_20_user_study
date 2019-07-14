/** 
 * Finds index of the first character in given array the differs from the given set of characters.
 * @return index of matched character or -1
 */
public static int findFirstDiff(final char[] source,final int index,final char match){
  for (int i=index; i < source.length; i++) {
    if (source[i] != match) {
      return i;
    }
  }
  return -1;
}

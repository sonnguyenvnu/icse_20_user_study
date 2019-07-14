/** 
 * Finds index of the first character in given charsequence the differs from the given set of characters.
 * @return index of matched character or -1
 */
public static int findFirstDiff(final CharSequence source,final int index,final CharSequence match){
  for (int i=index; i < source.length(); i++) {
    if (!equalsOne(source.charAt(i),match)) {
      return i;
    }
  }
  return -1;
}

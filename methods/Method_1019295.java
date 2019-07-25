/** 
 * Flips a range of bits, expanding the set size if necessary
 * @param startIndex lower index
 * @param endIndex one-past the last bit to flip
 */
public void flip(long startIndex,long endIndex){
  if (endIndex <= startIndex)   return;
  int startWord=(int)(startIndex >> 6);
  int endWord=expandingWordNum(endIndex - 1);
  long startmask=-1L << startIndex;
  long endmask=-1L >>> -endIndex;
  if (startWord == endWord) {
    bits[startWord]^=(startmask & endmask);
    return;
  }
  bits[startWord]^=startmask;
  for (int i=startWord + 1; i < endWord; i++) {
    bits[i]=~bits[i];
  }
  bits[endWord]^=endmask;
}

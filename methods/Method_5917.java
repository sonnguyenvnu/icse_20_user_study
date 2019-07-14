/** 
 * Finds the first NAL unit in  {@code data}. <p> If  {@code prefixFlags} is null then the first three bytes of a NAL unit must be entirelycontained within the part of the array being searched in order for it to be found. <p> When  {@code prefixFlags} is non-null, this method supports finding NAL units whose first fourbytes span  {@code data} arrays passed to successive calls. To use this feature, pass the same{@code prefixFlags} parameter to successive calls. State maintained in this parameter enablesthe detection of such NAL units. Note that when using this feature, the return value may be 3, 2 or 1 less than  {@code startOffset}, to indicate a NAL unit starting 3, 2 or 1 bytes before the first byte in the current array.
 * @param data The data to search.
 * @param startOffset The offset (inclusive) in the data to start the search.
 * @param endOffset The offset (exclusive) in the data to end the search.
 * @param prefixFlags A boolean array whose first three elements are used to store the staterequired to detect NAL units where the NAL unit prefix spans array boundaries. The array must be at least 3 elements long.
 * @return The offset of the NAL unit, or {@code endOffset} if a NAL unit was not found.
 */
public static int findNalUnit(byte[] data,int startOffset,int endOffset,boolean[] prefixFlags){
  int length=endOffset - startOffset;
  Assertions.checkState(length >= 0);
  if (length == 0) {
    return endOffset;
  }
  if (prefixFlags != null) {
    if (prefixFlags[0]) {
      clearPrefixFlags(prefixFlags);
      return startOffset - 3;
    }
 else     if (length > 1 && prefixFlags[1] && data[startOffset] == 1) {
      clearPrefixFlags(prefixFlags);
      return startOffset - 2;
    }
 else     if (length > 2 && prefixFlags[2] && data[startOffset] == 0 && data[startOffset + 1] == 1) {
      clearPrefixFlags(prefixFlags);
      return startOffset - 1;
    }
  }
  int limit=endOffset - 1;
  for (int i=startOffset + 2; i < limit; i+=3) {
    if ((data[i] & 0xFE) != 0) {
    }
 else     if (data[i - 2] == 0 && data[i - 1] == 0 && data[i] == 1) {
      if (prefixFlags != null) {
        clearPrefixFlags(prefixFlags);
      }
      return i - 2;
    }
 else {
      i-=2;
    }
  }
  if (prefixFlags != null) {
    prefixFlags[0]=length > 2 ? (data[endOffset - 3] == 0 && data[endOffset - 2] == 0 && data[endOffset - 1] == 1) : length == 2 ? (prefixFlags[2] && data[endOffset - 2] == 0 && data[endOffset - 1] == 1) : (prefixFlags[1] && data[endOffset - 1] == 1);
    prefixFlags[1]=length > 1 ? data[endOffset - 2] == 0 && data[endOffset - 1] == 0 : prefixFlags[2] && data[endOffset - 1] == 0;
    prefixFlags[2]=data[endOffset - 1] == 0;
  }
  return endOffset;
}

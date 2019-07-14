/** 
 * Finds the next occurrence of the NAL start code from a given index.
 * @param data The data in which to search.
 * @param index The first index to test.
 * @return The index of the first byte of the found start code, or {@link C#INDEX_UNSET}.
 */
private static int findNalStartCode(byte[] data,int index){
  int endIndex=data.length - NAL_START_CODE.length;
  for (int i=index; i <= endIndex; i++) {
    if (isNalStartCode(data,i)) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}

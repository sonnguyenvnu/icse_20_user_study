/** 
 * Returns the sample index of the closest synchronization sample at or before the given timestamp, if one is available.
 * @param timeUs Timestamp adjacent to which to find a synchronization sample.
 * @return Index of the synchronization sample, or {@link C#INDEX_UNSET} if none.
 */
public int getIndexOfEarlierOrEqualSynchronizationSample(long timeUs){
  int startIndex=Util.binarySearchFloor(timestampsUs,timeUs,true,false);
  for (int i=startIndex; i >= 0; i--) {
    if ((flags[i] & C.BUFFER_FLAG_KEY_FRAME) != 0) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}

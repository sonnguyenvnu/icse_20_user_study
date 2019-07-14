/** 
 * Returns the sample index of the closest synchronization sample at or after the given timestamp, if one is available.
 * @param timeUs Timestamp adjacent to which to find a synchronization sample.
 * @return index Index of the synchronization sample, or {@link C#INDEX_UNSET} if none.
 */
public int getIndexOfLaterOrEqualSynchronizationSample(long timeUs){
  int startIndex=Util.binarySearchCeil(timestampsUs,timeUs,true,false);
  for (int i=startIndex; i < timestampsUs.length; i++) {
    if ((flags[i] & C.BUFFER_FLAG_KEY_FRAME) != 0) {
      return i;
    }
  }
  return C.INDEX_UNSET;
}

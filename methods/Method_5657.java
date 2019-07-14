/** 
 * Returns the index of the first window in the playback order depending on whether shuffling is enabled.
 * @param shuffleModeEnabled Whether shuffling is enabled.
 * @return The index of the first window in the playback order, or {@link C#INDEX_UNSET} if thetimeline is empty.
 */
public int getFirstWindowIndex(boolean shuffleModeEnabled){
  return isEmpty() ? C.INDEX_UNSET : 0;
}

/** 
 * Finds the sample in the specified range that's before or at the specified time. If {@code keyframe} is {@code true} then the sample is additionally required to be a keyframe.
 * @param relativeStartIndex The relative index from which to start searching.
 * @param length The length of the range being searched.
 * @param timeUs The specified time.
 * @param keyframe Whether only keyframes should be considered.
 * @return The offset from {@code relativeFirstIndex} to the found sample, or -1 if no matchingsample was found.
 */
private int findSampleBefore(int relativeStartIndex,int length,long timeUs,boolean keyframe){
  int sampleCountToTarget=-1;
  int searchIndex=relativeStartIndex;
  for (int i=0; i < length && timesUs[searchIndex] <= timeUs; i++) {
    if (!keyframe || (flags[searchIndex] & C.BUFFER_FLAG_KEY_FRAME) != 0) {
      sampleCountToTarget=i;
    }
    searchIndex++;
    if (searchIndex == capacity) {
      searchIndex=0;
    }
  }
  return sampleCountToTarget;
}

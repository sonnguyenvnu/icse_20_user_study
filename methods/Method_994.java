/** 
 * Given an array of frame durations, generate an array of timestamps corresponding to when each frame beings.
 * @param frameDurationsMs an array of frame durations
 * @return an array of timestamps
 */
public int[] getFrameTimeStampsFromDurations(int[] frameDurationsMs){
  int[] frameTimestampsMs=new int[frameDurationsMs.length];
  int accumulatedDurationMs=0;
  for (int i=0; i < frameDurationsMs.length; i++) {
    frameTimestampsMs[i]=accumulatedDurationMs;
    accumulatedDurationMs+=frameDurationsMs[i];
  }
  return frameTimestampsMs;
}

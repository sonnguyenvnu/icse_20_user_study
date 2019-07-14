/** 
 * Returns whether it's possible to apply the specified edit using gapless playback info. 
 */
private static boolean canApplyEditWithGaplessInfo(long[] timestamps,long duration,long editStartTime,long editEndTime){
  int lastIndex=timestamps.length - 1;
  int latestDelayIndex=Util.constrainValue(MAX_GAPLESS_TRIM_SIZE_SAMPLES,0,lastIndex);
  int earliestPaddingIndex=Util.constrainValue(timestamps.length - MAX_GAPLESS_TRIM_SIZE_SAMPLES,0,lastIndex);
  return timestamps[0] <= editStartTime && editStartTime < timestamps[latestDelayIndex] && timestamps[earliestPaddingIndex] < editEndTime && editEndTime <= duration;
}

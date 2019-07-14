/** 
 * Computes the ideal selected index ignoring buffer health.
 * @param nowMs The current time in the timebase of {@link Clock#elapsedRealtime()}, or  {@link Long#MIN_VALUE} to ignore blacklisting.
 * @param trackBitrates The estimated track bitrates. May differ from format bitrates if moreaccurate estimates of the current track bitrates are available.
 */
private int determineIdealSelectedIndex(long nowMs,int[] trackBitrates){
  long effectiveBitrate=bandwidthProvider.getAllocatedBandwidth();
  int lowestBitrateNonBlacklistedIndex=0;
  for (int i=0; i < length; i++) {
    if (nowMs == Long.MIN_VALUE || !isBlacklisted(i,nowMs)) {
      Format format=getFormat(i);
      if (canSelectFormat(format,trackBitrates[i],playbackSpeed,effectiveBitrate)) {
        return i;
      }
 else {
        lowestBitrateNonBlacklistedIndex=i;
      }
    }
  }
  return lowestBitrateNonBlacklistedIndex;
}

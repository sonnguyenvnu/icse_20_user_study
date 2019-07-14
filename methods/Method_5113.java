/** 
 * Returns an instance with the specified ad durations, in microseconds. 
 */
@CheckResult public AdPlaybackState withAdDurationsUs(long[][] adDurationUs){
  AdGroup[] adGroups=Arrays.copyOf(this.adGroups,this.adGroups.length);
  for (int adGroupIndex=0; adGroupIndex < adGroupCount; adGroupIndex++) {
    adGroups[adGroupIndex]=adGroups[adGroupIndex].withAdDurationsUs(adDurationUs[adGroupIndex]);
  }
  return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
}

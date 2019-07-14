/** 
 * Returns an instance with the number of ads in  {@code adGroupIndex} resolved to {@code adCount}. The ad count must be greater than zero.
 */
@CheckResult public AdPlaybackState withAdCount(int adGroupIndex,int adCount){
  Assertions.checkArgument(adCount > 0);
  if (adGroups[adGroupIndex].count == adCount) {
    return this;
  }
  AdGroup[] adGroups=Arrays.copyOf(this.adGroups,this.adGroups.length);
  adGroups[adGroupIndex]=this.adGroups[adGroupIndex].withAdCount(adCount);
  return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
}

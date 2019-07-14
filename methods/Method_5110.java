/** 
 * Returns an instance with the specified ad marked as skipped. 
 */
@CheckResult public AdPlaybackState withSkippedAd(int adGroupIndex,int adIndexInAdGroup){
  AdGroup[] adGroups=Arrays.copyOf(this.adGroups,this.adGroups.length);
  adGroups[adGroupIndex]=adGroups[adGroupIndex].withAdState(AD_STATE_SKIPPED,adIndexInAdGroup);
  return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
}

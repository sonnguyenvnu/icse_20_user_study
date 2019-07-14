/** 
 * Returns an instance with the specified ad marked as played. 
 */
@CheckResult public AdPlaybackState withPlayedAd(int adGroupIndex,int adIndexInAdGroup){
  AdGroup[] adGroups=Arrays.copyOf(this.adGroups,this.adGroups.length);
  adGroups[adGroupIndex]=adGroups[adGroupIndex].withAdState(AD_STATE_PLAYED,adIndexInAdGroup);
  return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
}

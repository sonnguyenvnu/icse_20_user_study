/** 
 * Returns an instance with the specified ad marked as having a load error. 
 */
@CheckResult public AdPlaybackState withAdLoadError(int adGroupIndex,int adIndexInAdGroup){
  AdGroup[] adGroups=Arrays.copyOf(this.adGroups,this.adGroups.length);
  adGroups[adGroupIndex]=adGroups[adGroupIndex].withAdState(AD_STATE_ERROR,adIndexInAdGroup);
  return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
}

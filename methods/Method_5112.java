/** 
 * Returns an instance with all ads in the specified ad group skipped (except for those already marked as played or in the error state).
 */
@CheckResult public AdPlaybackState withSkippedAdGroup(int adGroupIndex){
  AdGroup[] adGroups=Arrays.copyOf(this.adGroups,this.adGroups.length);
  adGroups[adGroupIndex]=adGroups[adGroupIndex].withAllAdsSkipped();
  return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
}

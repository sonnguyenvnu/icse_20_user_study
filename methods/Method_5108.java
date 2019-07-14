/** 
 * Returns an instance with the specified ad URI. 
 */
@CheckResult public AdPlaybackState withAdUri(int adGroupIndex,int adIndexInAdGroup,Uri uri){
  AdGroup[] adGroups=Arrays.copyOf(this.adGroups,this.adGroups.length);
  adGroups[adGroupIndex]=adGroups[adGroupIndex].withAdUri(uri,adIndexInAdGroup);
  return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
}

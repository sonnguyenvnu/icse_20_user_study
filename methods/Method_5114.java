/** 
 * Returns an instance with the specified ad resume position, in microseconds. 
 */
@CheckResult public AdPlaybackState withAdResumePositionUs(long adResumePositionUs){
  if (this.adResumePositionUs == adResumePositionUs) {
    return this;
  }
 else {
    return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
  }
}

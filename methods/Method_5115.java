/** 
 * Returns an instance with the specified content duration, in microseconds. 
 */
@CheckResult public AdPlaybackState withContentDurationUs(long contentDurationUs){
  if (this.contentDurationUs == contentDurationUs) {
    return this;
  }
 else {
    return new AdPlaybackState(adGroupTimesUs,adGroups,adResumePositionUs,contentDurationUs);
  }
}

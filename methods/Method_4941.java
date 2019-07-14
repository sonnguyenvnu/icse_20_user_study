/** 
 * Resolves the specified timeline period and position to a  {@link MediaPeriodId} that should beplayed, returning an identifier for an ad group if one needs to be played before the specified position, or an identifier for a content media period if not.
 * @param periodUid The uid of the timeline period to play.
 * @param positionUs The next content position in the period to play.
 * @param windowSequenceNumber The sequence number of the window in the buffered sequence ofwindows this period is part of.
 * @return The identifier for the first media period to play, taking into account unplayed ads.
 */
private MediaPeriodId resolveMediaPeriodIdForAds(Object periodUid,long positionUs,long windowSequenceNumber){
  timeline.getPeriodByUid(periodUid,period);
  int adGroupIndex=period.getAdGroupIndexForPositionUs(positionUs);
  if (adGroupIndex == C.INDEX_UNSET) {
    int nextAdGroupIndex=period.getAdGroupIndexAfterPositionUs(positionUs);
    long endPositionUs=nextAdGroupIndex == C.INDEX_UNSET ? C.TIME_UNSET : period.getAdGroupTimeUs(nextAdGroupIndex);
    return new MediaPeriodId(periodUid,windowSequenceNumber,endPositionUs);
  }
 else {
    int adIndexInAdGroup=period.getFirstAdIndexToPlay(adGroupIndex);
    return new MediaPeriodId(periodUid,adGroupIndex,adIndexInAdGroup,windowSequenceNumber);
  }
}

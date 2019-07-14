/** 
 * Resolves the specified timeline period and position to a  {@link MediaPeriodId} that should beplayed, returning an identifier for an ad group if one needs to be played before the specified position, or an identifier for a content media period if not.
 * @param periodUid The uid of the timeline period to play.
 * @param positionUs The next content position in the period to play.
 * @return The identifier for the first media period to play, taking into account unplayed ads.
 */
public MediaPeriodId resolveMediaPeriodIdForAds(Object periodUid,long positionUs){
  long windowSequenceNumber=resolvePeriodIndexToWindowSequenceNumber(periodUid);
  return resolveMediaPeriodIdForAds(periodUid,positionUs,windowSequenceNumber);
}

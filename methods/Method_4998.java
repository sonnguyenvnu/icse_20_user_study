/** 
 * Returns the track groups for the given period. Must not be called until after preparation completes. <p>Use  {@link #getMappedTrackInfo(int)} to get the track groups mapped to renderers.
 * @param periodIndex The period index.
 * @return The track groups for the period. May be {@link TrackGroupArray#EMPTY} for single streamcontent.
 */
public final TrackGroupArray getTrackGroups(int periodIndex){
  Assertions.checkNotNull(trackGroupArrays);
  return trackGroupArrays[periodIndex];
}

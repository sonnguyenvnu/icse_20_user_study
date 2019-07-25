/** 
 * Get the list of all media tracks, or only those that match specified types.
 * @param types zero or more track types, if none specified then all track types will be returned
 * @return track information, empty list if no tracks
 */
public List<? extends TrackInfo> tracks(TrackType... types){
  return TrackInformation.getTrackInfo(mediaInstance,types);
}

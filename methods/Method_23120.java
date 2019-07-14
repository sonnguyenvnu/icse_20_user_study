/** 
 * Returns the compression quality of a track.
 * @return compression quality
 */
public float getCompressionQuality(int track){
  return ((VideoTrack)tracks.get(track)).videoQuality;
}

/** 
 * Returns the time scale of the media in a track.
 * @param track Track index.
 * @return time scale
 * @see #setMovieTimeScale(long)
 */
public long getMediaTimeScale(int track){
  return tracks.get(track).mediaTimeScale;
}

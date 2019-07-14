/** 
 * Returns the track duration in the movie's time scale without taking the edit list into account. <p> The returned value is the media duration of a track in the movies's time scale.
 * @param track Track index.
 * @return unedited track duration
 */
public long getUneditedTrackDuration(int track){
  Track t=tracks.get(track);
  return t.mediaDuration * t.mediaTimeScale / movieTimeScale;
}

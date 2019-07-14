/** 
 * Sets the  {@link RepeatMode} and returns whether the repeat mode change has been fully handled.If not, it is necessary to seek to the current playback position.
 */
public boolean updateRepeatMode(@RepeatMode int repeatMode){
  this.repeatMode=repeatMode;
  return updateForPlaybackModeChange();
}

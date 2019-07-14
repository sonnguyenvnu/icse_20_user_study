/** 
 * Sets whether shuffling is enabled and returns whether the shuffle mode change has been fully handled. If not, it is necessary to seek to the current playback position.
 */
public boolean updateShuffleModeEnabled(boolean shuffleModeEnabled){
  this.shuffleModeEnabled=shuffleModeEnabled;
  return updateForPlaybackModeChange();
}

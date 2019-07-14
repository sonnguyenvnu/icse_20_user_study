/** 
 * Resets polling. Should be called whenever the audio track is paused or resumed. 
 */
public void reset(){
  if (audioTimestamp != null) {
    updateState(STATE_INITIALIZING);
  }
}

/** 
 * Called by the player as part of  {@link ExoPlayer#stop(boolean)}. 
 */
public void handleStop(){
  abandonAudioFocus(true);
}

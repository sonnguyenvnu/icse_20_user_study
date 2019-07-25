/** 
 * Pauses playback. Call start() to resume.
 */
@Override public boolean pause(){
  try {
    mCurrentMediaPlayer.pause();
    return true;
  }
 catch (  IllegalStateException e) {
    return false;
  }
}

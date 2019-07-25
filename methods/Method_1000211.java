/** 
 * Starts or resumes playback.
 */
@Override public boolean start(){
  try {
    mCurrentMediaPlayer.start();
    return true;
  }
 catch (  IllegalStateException e) {
    return false;
  }
}

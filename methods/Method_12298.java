/** 
 * Checks whether seeking forward can be performed.
 * @return true if GIF has at least 2 frames
 */
@Override public boolean canSeekForward(){
  return getNumberOfFrames() > 1;
}

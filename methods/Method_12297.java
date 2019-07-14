/** 
 * Checks whether seeking backward can be performed.
 * @return true if GIF has at least 2 frames
 */
@Override public boolean canSeekBackward(){
  return getNumberOfFrames() > 1;
}

/** 
 * @return true if GIF is animated (has at least 2 frames and positive duration), false otherwise
 */
public boolean isAnimated(){
  return mGifInfoHandle.getNumberOfFrames() > 1 && getDuration() > 0;
}

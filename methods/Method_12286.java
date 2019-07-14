/** 
 * @return true if GIF is animated (has at least 2 frames and positive duration), false otherwise
 */
public boolean isAnimated(){
  return mImageCount > 1 && mDuration > 0;
}

/** 
 * Returns the number of pixels if this is a video format whose  {@link #width} and {@link #height}are known, or  {@link #NO_VALUE} otherwise
 */
public int getPixelCount(){
  return width == NO_VALUE || height == NO_VALUE ? NO_VALUE : (width * height);
}

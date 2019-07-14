/** 
 * If set to a value  {@code > 1}, requests the decoder to subsample the original frames, returning a smaller frame buffer to save memory. The sample size is the number of pixels in either dimension that correspond to a single pixel in the decoded bitmap. For example, inSampleSize == 4 returns an image that is 1/4 the width/height of the original, and 1/16 the number of pixels. Values outside range  {@code <1, 65635>} are treated as 1.Unlike  {@link android.graphics.BitmapFactory.Options#inSampleSize}values which are not powers of 2 are also supported. Default value is 1.
 * @param inSampleSize the sample size
 */
public void setInSampleSize(@IntRange(from=1,to=Character.MAX_VALUE) int inSampleSize){
  if (inSampleSize < 1 || inSampleSize > Character.MAX_VALUE) {
    this.inSampleSize=1;
  }
 else {
    this.inSampleSize=(char)inSampleSize;
  }
}

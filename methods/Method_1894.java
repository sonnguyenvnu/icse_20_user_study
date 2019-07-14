/** 
 * This is a fast, native implementation for rounding a bitmap. It takes the given bitmap and modifies it to be circular. <p>This implementation does not change the underlying bitmap dimensions, it only sets pixels that are outside of the circle to a transparent color.
 * @param bitmap the bitmap to modify
 */
public static void toCircle(Bitmap bitmap,boolean antiAliased){
  Preconditions.checkNotNull(bitmap);
  nativeToCircleFilter(bitmap,antiAliased);
}

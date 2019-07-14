/** 
 * Checks whether the producer may be able to produce images of the specified size. This makes no promise about being able to produce images for a particular source, only generally being able to produce output of the desired resolution.
 * @param width the desired width
 * @param height the desired height
 * @return true if the producer can meet these needs
 */
public static boolean isImageBigEnough(int width,int height,ResizeOptions resizeOptions){
  if (resizeOptions == null) {
    return getAcceptableSize(width) >= BitmapUtil.MAX_BITMAP_SIZE && getAcceptableSize(height) >= (int)BitmapUtil.MAX_BITMAP_SIZE;
  }
 else {
    return getAcceptableSize(width) >= resizeOptions.width && getAcceptableSize(height) >= resizeOptions.height;
  }
}

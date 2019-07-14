/** 
 * Like  {@link GifDrawable#getAllocationByteCount()} but does not include memory needed for backing {@link android.graphics.Bitmap}. {@code Bitmap} in {@code GifDrawable} may be allocated at the time of creation or existing one may be reused if {@link GifDrawableBuilder#with(GifDrawable)}is used. This method assumes no subsampling (sample size = 1).<br> To calculate allocation byte count of  {@link GifDrawable} created from the same input source {@link #getDrawableAllocationByteCount(GifDrawable,int)}can be used.
 * @return possible size of the memory needed to store pixels excluding backing {@link android.graphics.Bitmap} and assuming no subsampling
 */
public long getAllocationByteCount(){
  return mPixelsBytesCount;
}

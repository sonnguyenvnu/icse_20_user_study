/** 
 * Provide a loaded and cached bitmap for display. This bitmap will not be recycled when it is no longer needed. Use this method if you loaded the bitmap with an image loader such as Picasso or Volley.
 * @param bitmap bitmap to be displayed.
 * @return an {@link ImageSource} instance.
 */
@NonNull public static ImageSource cachedBitmap(@NonNull Bitmap bitmap){
  if (bitmap == null) {
    throw new NullPointerException("Bitmap must not be null");
  }
  return new ImageSource(bitmap,true);
}

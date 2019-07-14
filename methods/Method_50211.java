/** 
 * Set compression quality [0-100] that will be used to save resulting Bitmap.
 */
public RxGalleryFinal cropropCompressionQuality(@IntRange(from=0) int compressQuality){
  configuration.setCompressionQuality(compressQuality);
  return this;
}

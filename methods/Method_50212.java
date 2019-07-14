/** 
 * Setter for max size for both width and height of bitmap that will be decoded from an input Uri and used in the view.
 * @param maxBitmapSize - size in pixels
 */
public RxGalleryFinal cropMaxBitmapSize(@IntRange(from=100) int maxBitmapSize){
  configuration.setMaxBitmapSize(maxBitmapSize);
  return this;
}

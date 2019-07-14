/** 
 * Enables or disables the use of local thumbnails as previews.
 * @param enabled
 * @return the modified builder instance
 */
public ImageRequestBuilder setLocalThumbnailPreviewsEnabled(boolean enabled){
  mLocalThumbnailPreviewsEnabled=enabled;
  return this;
}

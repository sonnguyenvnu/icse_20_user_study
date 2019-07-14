/** 
 * Checks whether the producer may be able to produce images of the specified size. This makes no promise about being able to produce images for a particular source, only generally being able to produce output of the desired resolution. <p> In this case, assumptions are made about the common size of EXIF thumbnails which is that they may be up to 512 pixels in each dimension.
 * @param resizeOptions the resize options from the current request
 * @return true if the producer can meet these needs
 */
@Override public boolean canProvideImageForSize(ResizeOptions resizeOptions){
  return ThumbnailSizeChecker.isImageBigEnough(COMMON_EXIF_THUMBNAIL_MAX_DIMENSION,COMMON_EXIF_THUMBNAIL_MAX_DIMENSION,resizeOptions);
}

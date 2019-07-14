/** 
 * Enable or disable tiling of the image. This does not apply to preview images which are always loaded as a single bitmap, and tiling cannot be disabled when displaying a region of the source image.
 * @param tile whether tiling should be enabled.
 * @return this instance for chaining.
 */
@NonNull public ImageSource tiling(boolean tile){
  this.tile=tile;
  return this;
}

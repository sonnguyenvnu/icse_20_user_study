/** 
 * Enable or disable tiling of the image. This does not apply to preview images which are always loaded as a single bitmap, and tiling cannot be disabled when displaying a region of the source image.
 * @return this instance for chaining.
 */
public ImageSource tiling(boolean tile){
  this.tile=tile;
  return this;
}

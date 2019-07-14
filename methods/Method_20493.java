/** 
 * Use canvas max bitmap width and height instead of the default 2048, to avoid redundant tiling.
 */
@NonNull private Point getMaxBitmapDimensions(Canvas canvas){
  return new Point(Math.min(canvas.getMaximumBitmapWidth(),maxTileWidth),Math.min(canvas.getMaximumBitmapHeight(),maxTileHeight));
}

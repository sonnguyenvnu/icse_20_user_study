/** 
 * In SDK 14 and above, use canvas max bitmap width and height instead of the default 2048, to avoid redundant tiling.
 */
private Point getMaxBitmapDimensions(Canvas canvas){
  int maxWidth=2048;
  int maxHeight=2048;
  if (VERSION.SDK_INT >= 14) {
    try {
      maxWidth=(Integer)Canvas.class.getMethod("getMaximumBitmapWidth").invoke(canvas);
      maxHeight=(Integer)Canvas.class.getMethod("getMaximumBitmapHeight").invoke(canvas);
    }
 catch (    Exception e) {
    }
  }
  return new Point(Math.min(maxWidth,maxTileWidth),Math.min(maxHeight,maxTileHeight));
}

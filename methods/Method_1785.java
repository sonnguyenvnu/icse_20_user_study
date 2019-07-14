/** 
 * Common code for checking that x + width and y + height are within image bounds
 * @param source the source Bitmap
 * @param x starting x coordinate of source image subset
 * @param y starting y coordinate of source image subset
 * @param width width of the source image subset
 * @param height height of the source image subset
 */
private static void checkFinalImageBounds(Bitmap source,int x,int y,int width,int height){
  Preconditions.checkArgument(x + width <= source.getWidth(),"x + width must be <= bitmap.width()");
  Preconditions.checkArgument(y + height <= source.getHeight(),"y + height must be <= bitmap.height()");
}

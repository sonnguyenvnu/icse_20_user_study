/** 
 * Internal function to actually handle setting a block of pixels that has already been properly cropped from the image to a valid region.
 */
protected void setImpl(PImage sourceImage,int sourceX,int sourceY,int sourceWidth,int sourceHeight,int targetX,int targetY){
  int sourceOffset=sourceY * sourceImage.pixelWidth + sourceX;
  int targetOffset=targetY * pixelWidth + targetX;
  for (int y=sourceY; y < sourceY + sourceHeight; y++) {
    System.arraycopy(sourceImage.pixels,sourceOffset,pixels,targetOffset,sourceWidth);
    sourceOffset+=sourceImage.pixelWidth;
    targetOffset+=pixelWidth;
  }
  updatePixels(targetX,targetY,sourceWidth,sourceHeight);
}

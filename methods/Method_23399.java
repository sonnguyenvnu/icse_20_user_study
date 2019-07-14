/** 
 * Internal function to actually handle getting a block of pixels that has already been properly cropped to a valid region. That is, x/y/w/h are guaranteed to be inside the image space, so the implementation can use the fastest possible pixel copying method.
 */
protected void getImpl(int sourceX,int sourceY,int sourceWidth,int sourceHeight,PImage target,int targetX,int targetY){
  int sourceIndex=sourceY * pixelWidth + sourceX;
  int targetIndex=targetY * target.pixelWidth + targetX;
  for (int row=0; row < sourceHeight; row++) {
    System.arraycopy(pixels,sourceIndex,target.pixels,targetIndex,sourceWidth);
    sourceIndex+=pixelWidth;
    targetIndex+=target.pixelWidth;
  }
}

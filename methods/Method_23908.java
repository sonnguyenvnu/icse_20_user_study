@Override protected void setImpl(PImage sourceImage,int sourceX,int sourceY,int sourceWidth,int sourceHeight,int targetX,int targetY){
  sourceImage.loadPixels();
  int sourceOffset=sourceX + sourceImage.pixelWidth * sourceY;
  PixelWriter pw=context.getPixelWriter();
  pw.setPixels(targetX,targetY,sourceWidth,sourceHeight,argbFormat,sourceImage.pixels,sourceOffset,sourceImage.pixelWidth);
  if (loaded) {
    int sourceStride=sourceImage.pixelWidth;
    int targetStride=pixelWidth;
    int targetOffset=targetX + targetY * targetStride;
    for (int i=0; i < sourceHeight; i++) {
      System.arraycopy(sourceImage.pixels,sourceOffset + i * sourceStride,pixels,targetOffset + i * targetStride,sourceWidth);
    }
  }
}

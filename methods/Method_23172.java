@Override protected void getImpl(int sourceX,int sourceY,int sourceWidth,int sourceHeight,PImage target,int targetX,int targetY){
  WritableRaster raster=getRaster();
  if (sourceWidth == target.pixelWidth && sourceHeight == target.pixelHeight) {
    raster.getDataElements(sourceX,sourceY,sourceWidth,sourceHeight,target.pixels);
    if (raster.getNumBands() == 3) {
      target.filter(OPAQUE);
    }
  }
 else {
    int[] temp=new int[sourceWidth * sourceHeight];
    raster.getDataElements(sourceX,sourceY,sourceWidth,sourceHeight,temp);
    int sourceOffset=0;
    int targetOffset=targetY * target.pixelWidth + targetX;
    for (int y=0; y < sourceHeight; y++) {
      if (raster.getNumBands() == 3) {
        for (int i=0; i < sourceWidth; i++) {
          target.pixels[targetOffset + i]=0xFF000000 | temp[sourceOffset + i];
        }
      }
 else {
        System.arraycopy(temp,sourceOffset,target.pixels,targetOffset,sourceWidth);
      }
      sourceOffset+=sourceWidth;
      targetOffset+=target.pixelWidth;
    }
  }
}

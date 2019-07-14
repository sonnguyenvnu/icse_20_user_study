@Override protected void setImpl(PImage sourceImage,int sourceX,int sourceY,int sourceWidth,int sourceHeight,int targetX,int targetY){
  WritableRaster raster=getRaster();
  if ((sourceX == 0) && (sourceY == 0) && (sourceWidth == sourceImage.pixelWidth) && (sourceHeight == sourceImage.pixelHeight)) {
    raster.setDataElements(targetX,targetY,sourceImage.pixelWidth,sourceImage.pixelHeight,sourceImage.pixels);
  }
 else {
    PImage temp=sourceImage.get(sourceX,sourceY,sourceWidth,sourceHeight);
    raster.setDataElements(targetX,targetY,temp.pixelWidth,temp.pixelHeight,temp.pixels);
  }
}

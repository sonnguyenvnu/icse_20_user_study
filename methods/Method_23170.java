@Override public void loadPixels(){
  if (pixels == null || (pixels.length != pixelWidth * pixelHeight)) {
    pixels=new int[pixelWidth * pixelHeight];
  }
  WritableRaster raster=getRaster();
  raster.getDataElements(0,0,pixelWidth,pixelHeight,pixels);
  if (raster.getNumBands() == 3) {
    for (int i=0; i < pixels.length; i++) {
      pixels[i]=0xff000000 | pixels[i];
    }
  }
}

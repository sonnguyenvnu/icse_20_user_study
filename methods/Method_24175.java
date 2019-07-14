protected void allocatePixels(){
  updatePixelSize();
  if ((pixels == null) || (pixels.length != pixelWidth * pixelHeight)) {
    pixels=new int[pixelWidth * pixelHeight];
    pixelBuffer=PGL.allocateIntBuffer(pixels);
    loaded=false;
  }
}

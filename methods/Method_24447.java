public void setNative(int[] pixels,int x,int y,int w,int h){
  updatePixelBuffer(pixels);
  setNative(pixelBuffer,x,y,w,h);
  releasePixelBuffer();
}

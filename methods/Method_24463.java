protected void updatePixelBuffer(int[] pixels){
  pixelBuffer=PGL.updateIntBuffer(pixelBuffer,pixels,true);
  pixBufUpdateCount++;
}

protected void createPixelBuffer(){
  pixelBuffer=IntBuffer.allocate(width * height);
  pixelBuffer.rewind();
}

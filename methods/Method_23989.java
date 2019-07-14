protected int getColorValue(int scrX,int scrY){
  if (colorBuffer == null) {
    colorBuffer=IntBuffer.allocate(1);
  }
  colorBuffer.rewind();
  readPixels(scrX,graphics.height - scrY - 1,1,1,RGBA,UNSIGNED_BYTE,colorBuffer);
  return colorBuffer.get();
}

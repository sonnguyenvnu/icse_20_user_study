public void getPixels(int[] pixels){
  if (pixelBuffer != null) {
    pixelBuffer.get(pixels,0,pixels.length);
    pixelBuffer.rewind();
  }
}

private static void checkWidthHeight(int width,int height){
  if (width <= 0) {
    throw new IllegalArgumentException("width must be > 0");
  }
  if (height <= 0) {
    throw new IllegalArgumentException("height must be > 0");
  }
}

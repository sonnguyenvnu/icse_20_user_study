private static void checkXYSign(int x,int y){
  if (x < 0) {
    throw new IllegalArgumentException("x must be >= 0");
  }
  if (y < 0) {
    throw new IllegalArgumentException("y must be >= 0");
  }
}

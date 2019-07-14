public static long make(float width,float height){
  final int wBits=Float.floatToRawIntBits(width);
  final int hBits=Float.floatToRawIntBits(height);
  return ((long)wBits) << 32 | ((long)hBits);
}

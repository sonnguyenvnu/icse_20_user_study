public static int maxCompressedLength(int sourceLength){
  return 32 + sourceLength + sourceLength / 6;
}

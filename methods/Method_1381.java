private static boolean matchBytePattern(final byte[] byteArray,final int offset,final byte[] pattern){
  if (pattern == null || byteArray == null) {
    return false;
  }
  if (pattern.length + offset > byteArray.length) {
    return false;
  }
  for (int i=0; i < pattern.length; ++i) {
    if (byteArray[i + offset] != pattern[i]) {
      return false;
    }
  }
  return true;
}

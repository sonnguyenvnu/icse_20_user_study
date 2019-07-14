private static int findNextUnescapeIndex(byte[] bytes,int offset,int limit){
  for (int i=offset; i < limit - 2; i++) {
    if (bytes[i] == 0x00 && bytes[i + 1] == 0x00 && bytes[i + 2] == 0x03) {
      return i;
    }
  }
  return limit;
}

static boolean charArrayCompare(String src,int offset,char[] dest){
  final int destLen=dest.length;
  if (destLen + offset > src.length()) {
    return false;
  }
  for (int i=0; i < destLen; ++i) {
    if (dest[i] != src.charAt(offset + i)) {
      return false;
    }
  }
  return true;
}

public static boolean increment(byte[] bytes){
  final int startIndex=0;
  int i;
  for (i=bytes.length - 1; i >= startIndex; i--) {
    bytes[i]++;
    if (bytes[i] != 0) {
      break;
    }
  }
  return (i >= startIndex || bytes[startIndex] != 0);
}

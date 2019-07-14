private static int getAllocateLengthExp(int minExp,int maxExp,int length){
  assert (1 << maxExp) >= length;
  int part=length >>> minExp;
  if (part <= 0) {
    return 1 << minExp;
  }
  return 1 << 32 - Integer.numberOfLeadingZeros(length - 1);
}

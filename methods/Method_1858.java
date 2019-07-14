@VisibleForTesting public static int roundToPowerOfTwo(final int sampleSize){
  int compare=1;
  while (true) {
    if (compare >= sampleSize) {
      return compare;
    }
    compare*=2;
  }
}

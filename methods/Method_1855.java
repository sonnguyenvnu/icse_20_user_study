@VisibleForTesting public static int ratioToSampleSize(final float ratio){
  if (ratio > 0.5f + 0.5f * INTERVAL_ROUNDING) {
    return 1;
  }
  int sampleSize=2;
  while (true) {
    double intervalLength=1.0 / (Math.pow(sampleSize,2) - sampleSize);
    double compare=(1.0 / sampleSize) + (intervalLength * INTERVAL_ROUNDING);
    if (compare <= ratio) {
      return sampleSize - 1;
    }
    sampleSize++;
  }
}

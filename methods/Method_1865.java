@VisibleForTesting public static int roundNumerator(float maxRatio,float roundUpFraction){
  return (int)(roundUpFraction + maxRatio * SCALE_DENOMINATOR);
}

/** 
 * Applies  {@link #scaleLargeTimestamp(long,long,long)} to an array of unscaled timestamps.
 * @param timestamps The timestamps to scale.
 * @param multiplier The multiplier.
 * @param divisor The divisor.
 */
public static void scaleLargeTimestampsInPlace(long[] timestamps,long multiplier,long divisor){
  if (divisor >= multiplier && (divisor % multiplier) == 0) {
    long divisionFactor=divisor / multiplier;
    for (int i=0; i < timestamps.length; i++) {
      timestamps[i]/=divisionFactor;
    }
  }
 else   if (divisor < multiplier && (multiplier % divisor) == 0) {
    long multiplicationFactor=multiplier / divisor;
    for (int i=0; i < timestamps.length; i++) {
      timestamps[i]*=multiplicationFactor;
    }
  }
 else {
    double multiplicationFactor=(double)multiplier / divisor;
    for (int i=0; i < timestamps.length; i++) {
      timestamps[i]=(long)(timestamps[i] * multiplicationFactor);
    }
  }
}

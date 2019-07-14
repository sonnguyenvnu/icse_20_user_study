/** 
 * Computes a percentile by integration.
 * @param percentile The desired percentile, expressed as a fraction in the range (0,1].
 * @return The requested percentile value or {@link Float#NaN} if no samples have been added.
 */
public float getPercentile(float percentile){
  ensureSortedByValue();
  float desiredWeight=percentile * totalWeight;
  int accumulatedWeight=0;
  for (int i=0; i < samples.size(); i++) {
    Sample currentSample=samples.get(i);
    accumulatedWeight+=currentSample.weight;
    if (accumulatedWeight >= desiredWeight) {
      return currentSample.value;
    }
  }
  return samples.isEmpty() ? Float.NaN : samples.get(samples.size() - 1).value;
}

public int increment(int featureIndex,int incr){
  if (featureIndex < 0 || featureIndex > alphabet.size())   throw new IllegalArgumentException("featureIndex " + featureIndex + " out of range");
  return featureCounts.putOrAdd(featureIndex,incr,incr);
}

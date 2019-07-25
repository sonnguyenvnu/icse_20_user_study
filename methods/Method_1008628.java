@Override public double metric(long owningBucketOrd){
  if (valuesSource == null || owningBucketOrd >= maxes.size()) {
    return Double.NEGATIVE_INFINITY;
  }
  return maxes.get(owningBucketOrd);
}

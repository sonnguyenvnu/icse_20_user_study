@Override public double metric(long owningBucketOrd){
  if (valuesSource == null || owningBucketOrd >= sums.size()) {
    return Double.NaN;
  }
  return sums.get(owningBucketOrd) / counts.get(owningBucketOrd);
}

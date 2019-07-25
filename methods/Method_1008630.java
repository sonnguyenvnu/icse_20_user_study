@Override public double metric(long owningBucketOrd){
  if (valuesSource == null || owningBucketOrd >= mins.size()) {
    return Double.POSITIVE_INFINITY;
  }
  return mins.get(owningBucketOrd);
}

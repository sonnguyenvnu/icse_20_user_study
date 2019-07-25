@Override public double metric(long owningBucketOrd){
  return counts == null ? 0 : counts.cardinality(owningBucketOrd);
}

@Override protected RBMBackedTimestampSet _apply(final RBMBackedTimestampSet a,final RBMBackedTimestampSet b){
  if (!b.getTimeBucket().equals(a.getTimeBucket())) {
    throw new RuntimeException("Can't aggregate two RBMBackedTimestampSet with different time buckets: " + "a had bucket " + a.getTimeBucket() + ", b had bucket " + b.getTimeBucket());
  }
  a.addAll(b);
  return a;
}

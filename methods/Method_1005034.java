@Override protected LongTimeSeries _apply(final LongTimeSeries a,final LongTimeSeries b){
  if (!b.getTimeBucket().equals(a.getTimeBucket())) {
    throw new RuntimeException("Can't aggregate two LongTimeSeries with different time buckets: " + "a had bucket " + a.getTimeBucket() + ", b had bucket " + b.getTimeBucket());
  }
  for (  final Map.Entry<Instant,Long> entry : b.getTimeSeries().entrySet()) {
    a.upsert(entry.getKey(),entry.getValue());
  }
  return a;
}

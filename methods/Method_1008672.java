private MovAvgModel minimize(List<? extends InternalMultiBucketAggregation.InternalBucket> buckets,MultiBucketsAggregation histo,MovAvgModel model){
  int counter=0;
  EvictingQueue<Double> values=new EvictingQueue<>(this.window);
  double[] test=new double[window];
  ListIterator<? extends InternalMultiBucketAggregation.InternalBucket> iter=buckets.listIterator(buckets.size());
  while (iter.hasPrevious() && counter < window) {
    Double thisBucketValue=resolveBucketValue(histo,iter.previous(),bucketsPaths()[0],gapPolicy);
    if (!(thisBucketValue == null || thisBucketValue.equals(Double.NaN))) {
      test[window - counter - 1]=thisBucketValue;
      counter+=1;
    }
  }
  if (counter < window) {
    return model;
  }
  counter=0;
  double[] train=new double[window];
  while (iter.hasPrevious() && counter < window) {
    Double thisBucketValue=resolveBucketValue(histo,iter.previous(),bucketsPaths()[0],gapPolicy);
    if (!(thisBucketValue == null || thisBucketValue.equals(Double.NaN))) {
      train[window - counter - 1]=thisBucketValue;
      counter+=1;
    }
  }
  if (counter < window) {
    return model;
  }
  for (  double v : train) {
    values.add(v);
  }
  return SimulatedAnealingMinimizer.minimize(model,values,test);
}

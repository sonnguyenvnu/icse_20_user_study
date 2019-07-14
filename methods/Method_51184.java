@Override public double computeForType(MetricKey<T> key,T node,boolean force,MetricOptions options,MetricMemoizer<T> memoizer){
  ParameterizedMetricKey<T> paramKey=ParameterizedMetricKey.getInstance(key,options);
  Double prev=memoizer.getMemo(paramKey);
  if (!force && prev != null) {
    return prev;
  }
  double val=key.getCalculator().computeFor(node,options);
  memoizer.memoize(paramKey,val);
  return val;
}

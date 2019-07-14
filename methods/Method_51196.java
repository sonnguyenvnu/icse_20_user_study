@Override public double computeForOperation(MetricKey<O> key,O node,MetricOptions options){
  return myComputer.computeForOperation(key,node,true,options,DummyMetricMemoizer.<O>getInstance());
}

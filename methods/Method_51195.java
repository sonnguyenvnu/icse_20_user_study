@Override public double computeForType(MetricKey<T> key,T node,MetricOptions options){
  return myComputer.computeForType(key,node,true,options,DummyMetricMemoizer.<T>getInstance());
}

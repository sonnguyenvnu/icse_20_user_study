@Override public double computeWithResultOption(MetricKey<O> key,T node,MetricOptions options,ResultOption option){
  return myComputer.computeWithResultOption(key,node,true,options,option,DummyProjectMemoizer.<T,O>getInstance());
}

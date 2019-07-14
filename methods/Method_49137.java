@Override public QueryProfiler addNested(String groupName){
  if (groupName.equals(AND_QUERY) || groupName.equals(OR_QUERY))   return this;
  int nextId=(subMetricCounter++);
  MutableMetrics nested=new MutableMetrics(metrics.getId() + "." + groupName + "_" + nextId,groupName);
  metrics.addNested(nested);
  return new TP3ProfileWrapper(nested);
}

@Override public double computeWithResultOption(MetricKey<O> key,T node,boolean force,MetricOptions options,ResultOption option,ProjectMemoizer<T,O> stats){
  List<O> ops=findOperations(node);
  List<Double> values=new ArrayList<>();
  for (  O op : ops) {
    if (key.supports(op)) {
      MetricMemoizer<O> opStats=stats.getOperationMemoizer(op.getQualifiedName());
      double val=this.computeForOperation(key,op,force,options,opStats);
      if (val != Double.NaN) {
        values.add(val);
      }
    }
  }
switch (option) {
case SUM:
    return sum(values);
case HIGHEST:
  return highest(values);
case AVERAGE:
return average(values);
default :
return Double.NaN;
}
}

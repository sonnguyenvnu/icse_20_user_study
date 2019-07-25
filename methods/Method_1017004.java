@Override public AggregationInstance reducer(){
  return newInstance(of,each.reducer());
}

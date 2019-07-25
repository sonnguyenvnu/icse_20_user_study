@Override public AggregationInstance distributed(){
  return new SpreadInstance(getSize(),getExtent());
}

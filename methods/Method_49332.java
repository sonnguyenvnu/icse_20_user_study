@Override public T getStart(){
  Preconditions.checkArgument(!isEmpty(),"There are no points in this interval");
  return (T)Collections.min(points,ComparableComparator.getInstance());
}

@Override public T getEnd(){
  Preconditions.checkArgument(!isEmpty(),"There are no points in this interval");
  return (T)Collections.max(points,ComparableComparator.getInstance());
}

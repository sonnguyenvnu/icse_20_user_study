@Override public SliceQuery setLimit(int limit){
  Preconditions.checkArgument(!hasLimit());
  super.setLimit(limit);
  return this;
}

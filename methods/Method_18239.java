@Override public boolean retainAll(Collection<?> collection){
  boolean removed=false;
  for (int i=size() - 1; i >= 0; --i) {
    if (!collection.contains(valueAt(i))) {
      removeAt(i);
      removed=true;
    }
  }
  return removed;
}

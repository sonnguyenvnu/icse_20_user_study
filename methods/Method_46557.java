@Override public boolean retainAll(Collection<?> c){
  boolean modified=false;
  for (  Object o : c) {
    if (!contains(o)) {
      remove(o);
      modified=true;
    }
  }
  return modified;
}

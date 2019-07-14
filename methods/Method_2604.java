@Override public boolean removeAll(Collection<?> c){
  boolean modified=false;
  Iterator<?> it=iterator();
  while (it.hasNext()) {
    if (c.contains(it.next())) {
      it.remove();
      modified=true;
    }
  }
  return modified;
}

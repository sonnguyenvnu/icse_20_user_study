@Override public boolean removeAll(Collection<?> collection){
  boolean result=false;
  Iterator<?> it=iterator();
  while (it.hasNext()) {
    if (collection.contains(it.next())) {
      it.remove();
      result=true;
    }
  }
  return result;
}

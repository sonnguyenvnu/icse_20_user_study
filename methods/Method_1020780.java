@Override public T poll(){
  T v=get();
  if (v != null) {
    lazySet(null);
  }
  return v;
}

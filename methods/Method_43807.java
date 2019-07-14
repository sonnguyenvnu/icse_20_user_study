public final List<T> getList(){
  return Collections.unmodifiableList(getResultsList());
}

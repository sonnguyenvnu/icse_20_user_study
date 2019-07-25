@Override public void add(T t,Consumer<T> fi){
  storeBatch(tryCreateBatch(t),fi);
}

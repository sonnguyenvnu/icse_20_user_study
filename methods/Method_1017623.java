private <T>List<T> rtrim(final int size,final List<T> a){
  return new AbstractList<T>(){
    @Override public T get(    int index){
      return a.get(index);
    }
    @Override public int size(){
      return Math.max(0,a.size() - size);
    }
  }
;
}

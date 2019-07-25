private <T>List<T> append(final List<T> a,final List<T> b){
  return new AbstractList<T>(){
    @Override public T get(    int index){
      return index < a.size() ? a.get(index) : b.get(index - a.size());
    }
    @Override public int size(){
      return a.size() + b.size();
    }
  }
;
}

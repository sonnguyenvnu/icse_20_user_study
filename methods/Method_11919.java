private static <T>List<T> asReversedList(final List<T> list){
  return new AbstractList<T>(){
    @Override public T get(    int index){
      return list.get(list.size() - index - 1);
    }
    @Override public int size(){
      return list.size();
    }
  }
;
}

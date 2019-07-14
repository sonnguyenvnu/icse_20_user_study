public static <T,U>Comparator<T> comparing(Function<? super T,? extends U> keyExtractor,Comparator<? super U> keyComparator){
  Objects.requireNonNull(keyExtractor);
  Objects.requireNonNull(keyComparator);
  return (Comparator<T> & Serializable)(object1,object2) -> keyComparator.compare(keyExtractor.apply(object1),keyExtractor.apply(object2));
}

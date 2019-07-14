@SuppressWarnings("unchecked") public static <T extends Comparable<? super T>>Comparator<T> naturalOrder(){
  return (Comparator<T>)Comparators.NaturalOrderComparator.INSTANCE;
}

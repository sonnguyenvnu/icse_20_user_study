public static <T>Comparator<T> reversed(Comparator<T> comparator){
  return Collections.reverseOrder(comparator);
}

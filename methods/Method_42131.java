private static <T>Comparator<T> reverse(Comparator<T> comparator){
  return (o1,o2) -> comparator.compare(o2,o1);
}

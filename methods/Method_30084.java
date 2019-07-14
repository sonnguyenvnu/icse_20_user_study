static <T>TertiaryOperator<T> maxBy(Comparator<? super T> comparator){
  Objects.requireNonNull(comparator);
  return (a,b,c) -> comparator.compare(a,b) >= 0 ? comparator.compare(a,c) >= 0 ? a : c : comparator.compare(b,c) >= 0 ? b : c;
}

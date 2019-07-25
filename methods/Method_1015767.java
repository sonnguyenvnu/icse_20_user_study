public static <E>List<E> cdr(List<E> l){
  return l.isEmpty() ? Collections.emptyList() : l.subList(1,l.size());
}

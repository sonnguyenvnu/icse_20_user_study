public static <E>List<E> car(List<E> l){
  return l.isEmpty() ? Collections.emptyList() : Collections.singletonList(l.get(0));
}

private static <T>void classify(Set<T> src,Set<T> des,ClassifyStandard<T> standard){
  Set<T> s=new HashSet<>();
  for (  T t : src) {
    if (standard.satisfy(t)) {
      s.add(t);
    }
  }
  src.removeAll(s);
  des.addAll(s);
}

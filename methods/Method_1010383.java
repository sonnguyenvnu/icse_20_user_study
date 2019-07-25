public static <T>Set<T> union(Set<T>... sets){
  Set<T> result=new LinkedHashSet<>();
  for (  Set<T> s : sets) {
    result.addAll(s);
  }
  return result;
}

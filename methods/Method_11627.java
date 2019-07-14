public static <T>Set<T> newHashSet(T... t){
  Set<T> set=new HashSet<T>(t.length);
  for (  T t1 : t) {
    set.add(t1);
  }
  return set;
}

@Deprecated public static <T>Set<T> filter(Set<T> ts,Condition<T> f){
  Set<T> result=new HashSet<>();
  for (  T t : ts) {
    if (f.met(t)) {
      result.add(t);
    }
  }
  return result;
}

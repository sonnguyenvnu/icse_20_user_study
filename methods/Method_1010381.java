@Deprecated public static <T>List<T> filter(List<? extends T> ts,Condition<T> f){
  List<T> result=new ArrayList<>();
  for (  T t : ts) {
    if (f.met(t)) {
      result.add(t);
    }
  }
  return result;
}

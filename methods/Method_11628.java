public static <T>List<T> newArrayList(T... t){
  List<T> set=new ArrayList<T>(t.length);
  for (  T t1 : t) {
    set.add(t1);
  }
  return set;
}

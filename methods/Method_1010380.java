public static <T,F extends T>List<F> filter(Class<F> cls,List<? extends T> l){
  List<F> result=new ArrayList<>();
  for (  T t : l) {
    if (cls.isInstance(t)) {
      result.add(cls.cast(t));
    }
  }
  return result;
}

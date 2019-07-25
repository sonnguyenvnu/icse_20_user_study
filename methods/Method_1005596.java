private static <T,R>List<R> transform(List<T> list,Function<T,R> fx){
  List<R> result=new ArrayList<>();
  for (  T element : list) {
    result.add(fx.apply(element));
  }
  return result;
}

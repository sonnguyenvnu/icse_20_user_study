static <T,R>CheckedFunction<T,R> fnOf(R result){
  return t -> result;
}

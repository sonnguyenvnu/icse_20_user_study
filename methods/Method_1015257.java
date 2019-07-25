public static <U,V>Function<U,V> memoize(Function<U,V> f){
  if (f instanceof MemoizedFunction) {
    return f;
  }
 else {
    return new MemoizedFunction<>(f);
  }
}

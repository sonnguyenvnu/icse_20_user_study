public static <T>Closure<T> returning(final T thing){
  return new PassThroughClosure<>(thing);
}

public static <A,T>Function<A,Try<T>> wrap(final FunctionWithException<A,T> function){
  return arg -> Try.cons(() -> function.apply(arg));
}

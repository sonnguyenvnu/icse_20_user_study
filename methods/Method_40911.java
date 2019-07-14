static <T,R>CheckedFunction<T,R> fnOf(CheckedSupplier<R> supplier){
  return t -> supplier.get();
}

static <T,R>CheckedFunction<T,R> fnOf(CheckedConsumer<T> consumer){
  return t -> {
    consumer.accept(t);
    return null;
  }
;
}

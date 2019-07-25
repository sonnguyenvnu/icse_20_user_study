public static <U>ISequence<U> singleton(U value){
  if (IGNORE_NULL_VALUES) {
    if (value == null) {
      return NullSequence.instance();
    }
  }
  return new BasicSequence<U>(Collections.singleton(value));
}

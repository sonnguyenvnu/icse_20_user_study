@SafeVarargs @SuppressWarnings("varargs") protected static <T>T get(T defaultValue,Predicate<? super T> accept,Supplier<T>... suppliers){
  return Iterables.find(Iterables.transform(Arrays.asList(suppliers),Supplier::get),accept::test,defaultValue);
}

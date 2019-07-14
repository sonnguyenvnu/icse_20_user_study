public static <T>Supplier<T> defaultInstanceGetter(Class<T> clazz){
  return new DefaultInstanceGetter<>(clazz);
}

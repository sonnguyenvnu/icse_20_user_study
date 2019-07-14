private static Set<Class<?>> nullableClassToSet(Class<?> nullableClass){
  return nullableClass == null ? Collections.<Class<?>>emptySet() : Collections.<Class<?>>singleton(nullableClass);
}

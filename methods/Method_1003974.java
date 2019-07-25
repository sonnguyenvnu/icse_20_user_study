public <K,V>CachingMethodProxy<T> cache(V value,Cache<K,V> cache,Function<Object[],K> keyBuilder,Predicate<V> valueFilter){
  Preconditions.checkNotNull(cache);
  Preconditions.checkNotNull(keyBuilder);
  Preconditions.checkNotNull(valueFilter);
  Preconditions.checkState(recordMode,"Cache setup is not allowed after prepare() is called.");
  Preconditions.checkState(lastMethodCall != null,"No method call captured to be cached.");
  Class<?> returnType=lastMethodCall.getReturnType();
  Preconditions.checkArgument(returnType != Void.TYPE,"Cannot cache results from void method: " + lastMethodCall);
  if (returnType.isPrimitive()) {
    returnType=AUTO_BOXING_MAP.get(returnType);
  }
  methodCaches.put(lastMethodCall,new MethodCache<K,V>(lastMethodCall,cache,keyBuilder,valueFilter));
  lastMethodCall=null;
  return this;
}

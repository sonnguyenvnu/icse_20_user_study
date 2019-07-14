/** 
 * Determine a cache key for the given method and target class. <p>Must not produce same key for overloaded methods. Must produce same key for different instances of the same method.
 * @param method      the method (never {@code null})
 * @param targetClass the target class (may be {@code null})
 * @return the cache key (never {@code null})
 */
protected Object getCacheKey(Method method,Class<?> targetClass){
  return new MethodClassKey(method,targetClass);
}

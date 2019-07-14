/** 
 * Sets a custom  {@code Equivalence} strategy for comparing keys.<p>By default, the cache uses  {@link Equivalence#identity} to determine key equality when
 * @link #weakKeys} is specified, and {@link Equivalence#equals()} otherwise.
 */
@Nonnull CacheBuilder<K,V> keyEquivalence(@Nonnull Equivalence<Object> equivalence){
  Preconditions.checkState(keyEquivalence == null,"key equivalence was already set to %s",keyEquivalence);
  keyEquivalence=Preconditions.checkNotNull(equivalence);
  return this;
}

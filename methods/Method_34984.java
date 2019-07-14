/** 
 * Sets a custom  {@code Equivalence} strategy for comparing values.<p>By default, the cache uses  {@link Equivalence#identity} to determine value equality when
 * @link #weakValues} or @link #softValues} is specified, and {@link Equivalence#equals()}otherwise.
 */
@Nonnull CacheBuilder<K,V> valueEquivalence(@Nonnull Equivalence<Object> equivalence){
  Preconditions.checkState(valueEquivalence == null,"value equivalence was already set to %s",valueEquivalence);
  this.valueEquivalence=Preconditions.checkNotNull(equivalence);
  return this;
}

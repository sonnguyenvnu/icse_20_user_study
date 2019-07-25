/** 
 * Specifies the weigher to use in determining the weight of entries. Entry weight is taken into consideration by  {@link #maximumWeight(long)} when determining which entries to evict, and useof this method requires a corresponding call to  {@link #maximumWeight(long)} prior to calling{@link #build}. Weights are measured and recorded when entries are inserted into the cache, and are thus effectively static during the lifetime of a cache entry. <p>When the weight of an entry is zero it will not be considered for size-based eviction (though it still may be evicted by other means). <p><b>Important note:</b> Instead of returning <em>this</em> as a  {@code CacheBuilder}instance, this method returns  {@code CacheBuilder<K1, V1>}. From this point on, either the original reference or the returned reference may be used to complete configuration and build the cache, but only the "generic" one is type-safe. That is, it will properly prevent you from building caches whose key or value types are incompatible with the types accepted by the weigher already provided; the  {@code CacheBuilder} type cannot do this. For best results,simply use the standard method-chaining idiom, as illustrated in the documentation at top, configuring a  {@code CacheBuilder} and building your {@link Cache} all in a single statement.<p><b>Warning:</b> if you ignore the above advice, and use this  {@code CacheBuilder} to build acache whose key or value type is incompatible with the weigher, you will likely experience a {@link ClassCastException} at some <i>undefined</i> point in the future.
 * @param weigher the weigher to use in calculating the weight of cache entries
 * @return this {@code CacheBuilder} instance (for chaining)
 * @throws IllegalArgumentException if {@code size} is negative
 * @throws IllegalStateException if a maximum size was already set
 * @since 11.0
 */
@GwtIncompatible public <K1 extends K,V1 extends V>CacheBuilder<K1,V1> weigher(Weigher<? super K1,? super V1> weigher){
  checkState(this.weigher == null);
  if (strictParsing) {
    checkState(this.maximumSize == UNSET_INT,"weigher can not be combined with maximum size",this.maximumSize);
  }
  @SuppressWarnings("unchecked") CacheBuilder<K1,V1> me=(CacheBuilder<K1,V1>)this;
  me.weigher=checkNotNull(weigher);
  return me;
}

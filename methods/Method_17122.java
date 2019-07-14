/** 
 * Specifies that each key (not value) stored in the cache should be wrapped in a {@link WeakReference} (by default, strong references are used).<p> <b>Warning:</b> when this method is used, the resulting cache will use identity ( {@code ==}) comparison to determine equality of keys. Its  {@link Cache#asMap} view will thereforetechnically violate the  {@link Map} specification (in the same way that {@link IdentityHashMap}does). <p> Entries with keys that have been garbage collected may be counted in {@link Cache#estimatedSize()}, but will never be visible to read or write operations; such entries are cleaned up as part of the routine maintenance described in the class javadoc. <p> This feature cannot be used in conjunction with  {@link #writer}.
 * @return this {@code Caffeine} instance (for chaining)
 * @throws IllegalStateException if the key strength was already set or the writer was set
 */
@NonNull public Caffeine<K,V> weakKeys(){
  requireState(keyStrength == null,"Key strength was already set to %s",keyStrength);
  requireState(writer == null,"Weak keys may not be used with CacheWriter");
  keyStrength=Strength.WEAK;
  return this;
}

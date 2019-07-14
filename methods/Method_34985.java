/** 
 * Specifies the maximum number of entries the cache may contain. Note that the cache <b>may evict an entry before this limit is exceeded</b>. As the cache size grows close to the maximum, the cache evicts entries that are less likely to be used again. For example, the cache may evict an entry because it hasn't been used recently or very often. <p>When  {@code size} is zero, elements will be evicted immediately after being loaded into thecache. This can be useful in testing, or to disable caching temporarily without a code change. <p>This feature cannot be used in conjunction with  {@link #maximumWeight}.
 * @param size the maximum size of the cache
 * @throws IllegalArgumentException if {@code size} is negative
 * @throws IllegalStateException if a maximum size or weight was already set
 */
@Nonnull public CacheBuilder<K,V> maximumSize(long size){
  Preconditions.checkState(this.maximumSize == UNSET_INT,"maximum size was already set to %s",this.maximumSize);
  Preconditions.checkState(this.maximumWeight == UNSET_INT,"maximum weight was already set to %s",this.maximumWeight);
  Preconditions.checkState(this.weigher == null,"maximum size can not be combined with weigher");
  Preconditions.checkArgument(size >= 0,"maximum size must not be negative");
  this.maximumSize=size;
  return this;
}

/** 
 * Specifies the maximum number of entries the cache may contain. Note that the cache <b>may evict an entry before this limit is exceeded or temporarily exceed the threshold while evicting</b>. As the cache size grows close to the maximum, the cache evicts entries that are less likely to be used again. For example, the cache may evict an entry because it hasn't been used recently or very often. <p> When  {@code size} is zero, elements will be evicted immediately after being loaded into thecache. This can be useful in testing, or to disable caching temporarily without a code change. <p> This feature cannot be used in conjunction with  {@link #maximumWeight}.
 * @param maximumSize the maximum size of the cache
 * @return this {@code Caffeine} instance (for chaining)
 * @throws IllegalArgumentException if {@code size} is negative
 * @throws IllegalStateException if a maximum size or weight was already set
 */
@NonNull public Caffeine<K,V> maximumSize(@NonNegative long maximumSize){
  requireState(this.maximumSize == UNSET_INT,"maximum size was already set to %s",this.maximumSize);
  requireState(this.maximumWeight == UNSET_INT,"maximum weight was already set to %s",this.maximumWeight);
  requireState(this.weigher == null,"maximum size can not be combined with weigher");
  requireArgument(maximumSize >= 0,"maximum size must not be negative");
  this.maximumSize=maximumSize;
  return this;
}

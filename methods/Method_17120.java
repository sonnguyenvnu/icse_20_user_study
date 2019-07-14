/** 
 * Specifies the maximum weight of entries the cache may contain. Weight is determined using the {@link Weigher} specified with {@link #weigher}, and use of this method requires a corresponding call to  {@link #weigher} prior to calling {@link #build}. <p> Note that the cache <b>may evict an entry before this limit is exceeded or temporarily exceed the threshold while evicting</b>. As the cache size grows close to the maximum, the cache evicts entries that are less likely to be used again. For example, the cache may evict an entry because it hasn't been used recently or very often. <p> When  {@code maximumWeight} is zero, elements will be evicted immediately after being loadedinto cache. This can be useful in testing, or to disable caching temporarily without a code change. <p> Note that weight is only used to determine whether the cache is over capacity; it has no effect on selecting which entry should be evicted next. <p> This feature cannot be used in conjunction with  {@link #maximumSize}.
 * @param maximumWeight the maximum total weight of entries the cache may contain
 * @return this {@code Caffeine} instance (for chaining)
 * @throws IllegalArgumentException if {@code maximumWeight} is negative
 * @throws IllegalStateException if a maximum weight or size was already set
 */
@NonNull public Caffeine<K,V> maximumWeight(@NonNegative long maximumWeight){
  requireState(this.maximumWeight == UNSET_INT,"maximum weight was already set to %s",this.maximumWeight);
  requireState(this.maximumSize == UNSET_INT,"maximum size was already set to %s",this.maximumSize);
  this.maximumWeight=maximumWeight;
  requireArgument(maximumWeight >= 0,"maximum weight must not be negative");
  return this;
}

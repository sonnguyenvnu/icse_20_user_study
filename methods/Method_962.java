/** 
 * Sets whether the drawable can use worker threads to optimistically prefetch frames.
 * @param allowPrefetching whether the backend can use worker threads to prefetch frames
 * @return this builder
 */
public AnimatedDrawableOptionsBuilder setAllowPrefetching(boolean allowPrefetching){
  mAllowPrefetching=allowPrefetching;
  return this;
}
